package com.example.payskytask.appFeature.presentation.postsList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ListenableWorker.Result.Success
import com.example.payskytask.appFeature.data.model.DataModelItem
import com.example.payskytask.appFeature.presentation.postsList.PostsListUseCases.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.payskytask.appFeature.domain.UTILS.Result
import com.example.payskytask.appFeature.presentation.postsList.deleteUseCase.DeletePostsUseCase
import kotlinx.coroutines.Dispatchers

@HiltViewModel
class PostsViewModel @Inject constructor(
    val getPostsUseCase: GetPostsUseCase,
    val deleteUseCase: DeletePostsUseCase
) : ViewModel() {
    private var _Posts = MutableLiveData<List<DataModelItem>>()
    val Posts: LiveData<List<DataModelItem>> get() = _Posts

    init {
        viewModelScope.launch(Dispatchers.Main) {
            fetchData()
        }

    }

    suspend fun fetchData() {
        if (getPostsUseCase() != null)
            getPostsUseCase()!!.observeForever {
                _Posts.postValue(it)
            }
    }


    fun deleteItem(item: DataModelItem) {
        viewModelScope.launch {
            deleteUseCase(item)
        }
    }

}