package com.example.payskytask.appFeature.data.repository

import androidx.lifecycle.LiveData
import com.example.payskytask.appFeature.data.dataStore.DataStoreIMP
import com.example.payskytask.appFeature.data.model.DataModelItem
import com.example.payskytask.appFeature.domain.UTILS.Result
import com.example.payskytask.appFeature.domain.dataStore.DataStore
import com.example.payskytask.appFeature.domain.repository.Repository
import javax.inject.Inject

class RepositoryIMP @Inject constructor(private val dataStore: DataStoreIMP) : Repository {
    override suspend fun fetchData(): Result<LiveData<List<DataModelItem>>> {
        return dataStore.fetchData()
    }

    override suspend fun insertItem(model: DataModelItem) {
        dataStore.insert(model)
    }

    override suspend fun getPost(id: Int): DataModelItem? {
        return dataStore.getPost(id)
    }

    override suspend fun updateItem(model: DataModelItem) {
        dataStore.update(model)
    }

    override suspend fun deleteItem(model: DataModelItem) {
        dataStore.delete(model)
    }

}