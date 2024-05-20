package com.example.payskytask.appFeature.domain.dataStore

import androidx.lifecycle.LiveData
import com.example.payskytask.appFeature.data.model.DataModelItem
import com.example.payskytask.appFeature.domain.UTILS.Result

interface DataStore {
    suspend fun fetchData(): Result<LiveData<List<DataModelItem>>>
    suspend fun insert(model: DataModelItem)
    suspend fun getPost(id: Int): DataModelItem?

    suspend fun update(model: DataModelItem)
    suspend fun delete(model: DataModelItem)
    suspend fun isNetworkAvailable(): Boolean
    suspend fun scheduleSync()
}