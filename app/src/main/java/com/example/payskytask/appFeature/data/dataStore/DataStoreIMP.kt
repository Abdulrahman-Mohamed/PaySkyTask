package com.example.payskytask.appFeature.data.dataStore

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.payskytask.appFeature.data.data_source.ApiService
import com.example.payskytask.appFeature.data.data_source.UserDao
import com.example.payskytask.appFeature.data.model.DataModelItem
import com.example.payskytask.appFeature.data.worker.SyncWorker
import com.example.payskytask.appFeature.domain.dataStore.DataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import com.example.payskytask.appFeature.domain.UTILS.Result

data class DataStoreIMP @Inject constructor(
    val dao: UserDao,
    val api: ApiService,
    @ApplicationContext val context: Context
) : DataStore {
    override suspend fun fetchData(): Result<LiveData<List<DataModelItem>>> {
        // emit(Result.Loading)
        try {
            if (isNetworkAvailable()) {

                val response = api.fetchData()
                if (response.isSuccessful) {
                    response.body()!!.map { apiData ->
                        dao.insert(
                            DataModelItem(
                                apiData.body,
                                apiData.id,
                                apiData.title,
                                apiData.userId,
                                true,
                                false
                            )
                        )
                    }
                    return Result.Success(dao.getAllItems())

                } else {
                    if (dao != null)
                        return Result.ErrorWithResult(
                            response.errorBody()!!.toString(),
                            dao.getAllItems()
                        )
                    else
                        return (Result.Error(response.errorBody()!!.toString()))
                }
            } else
                return (Result.ErrorWithResult("No Network Connection", dao.getAllItems()))

        } catch (e: Exception) {
            // Handle exception
            return (
                    Result.Error(e.localizedMessage!!.toString())
                    )
        }

    }

    override suspend fun insert(model: DataModelItem) {
        if (isNetworkAvailable()) {
            try {
                val response = api.insertItem(model)
                if (response.isSuccessful) {
                    response.body()?.let { syncedItem ->
                        dao.insert(syncedItem.copy(isSynched = true))
                    }
                }
            } catch (e: Exception) {
                // Handle exception
            }
        } else {
            dao.insert(model.copy(isSynched = false))
            scheduleSync() // Schedule background sync
        }
    }

    override suspend fun getPost(id: Int): DataModelItem? {
        return dao.getItem(id)
    }

    override suspend fun update(model: DataModelItem) {
        dao.update(model)
        try {
            if (isNetworkAvailable()) {

                val response = api.updateItem(model.id, model)
                if (response.isSuccessful) {
                    dao.update(model.copy(isSynched = true))
                }
            } else {
                dao.update(model.copy(isSynched = false))
                scheduleSync() // Schedule background sync
            }
        } catch (e: Exception) {
            // Handle exception


        }
    }

    override suspend fun delete(model: DataModelItem) {
        try {
            if (isNetworkAvailable()) {

                val response = api.deleteItem(model.id)
                dao.update(model.copy(isDeleted = true))

            } else {
                dao.update(model.copy(isSynched = false, isDeleted = true))
                scheduleSync()
                // Schedule background sync

            }
        } catch (e: Exception) {
            Log.e("TAG", "delete: " + e.localizedMessage)
        }
    }


    override suspend fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }

    override suspend fun scheduleSync() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val syncWorkRequest = OneTimeWorkRequestBuilder<SyncWorker>()
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(context).enqueue(syncWorkRequest)
    }
}