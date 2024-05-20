package com.example.payskytask.appFeature.domain.repository

import androidx.lifecycle.LiveData
import com.example.payskytask.appFeature.data.model.DataModelItem
import com.example.payskytask.appFeature.domain.UTILS.Result

interface Repository {
    suspend fun fetchData(): Result<LiveData<List<DataModelItem>>>
    suspend fun insertItem(model: DataModelItem)
    suspend fun getPost(id: Int): DataModelItem?
    suspend fun updateItem(model: DataModelItem)
    suspend fun deleteItem(model: DataModelItem)
}