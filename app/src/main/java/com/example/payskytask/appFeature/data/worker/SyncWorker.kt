package com.example.payskytask.appFeature.data.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.payskytask.appFeature.data.data_source.ApiService
import com.example.payskytask.appFeature.data.data_source.UserDao
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SyncWorker @Inject constructor(
    private val dao: UserDao,
    private val api: ApiService,
    applicationContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(applicationContext, workerParams) {
    override suspend fun doWork(): Result {


        val unsyncedItems = dao.getUnsyncedItems()
        for (item in unsyncedItems) {
            if (item.isDeleted) {
                try {
                    val response = api.deleteItem(item.id)
                    if (response.isSuccessful) {
                        dao.delete(item.id)
                    }
                } catch (e: Exception) {
                    return Result.retry()
                }
            } else {
                try {
                    val response = api.insertItem(item)
                    if (response.isSuccessful) {
                        dao.update(item.copy(isSynched = true))
                    }
                } catch (e: Exception) {
                    return Result.retry()
                }
            }
        }

        return Result.success()
    }
}