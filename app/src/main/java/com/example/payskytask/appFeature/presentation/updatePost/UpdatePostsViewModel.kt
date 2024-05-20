package com.example.payskytask.appFeature.presentation.updatePost

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.payskytask.appFeature.data.model.DataModelItem
import com.example.payskytask.appFeature.presentation.updatePost.updateUseCases.GetPostUseCase
import com.example.payskytask.appFeature.presentation.updatePost.updateUseCases.InsertPostUseCase
import com.example.payskytask.appFeature.presentation.updatePost.updateUseCases.UpdateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdatePostsViewModel @Inject constructor(
    val updatePosts: UpdateUseCase,
    val getPostUseCase: GetPostUseCase,
    val insetPostUseCase: InsertPostUseCase
) : ViewModel() {

    var _data = MutableLiveData<DataModelItem>()
    val data: LiveData<DataModelItem> get() = _data

    fun updatePost(data: DataModelItem) {
        viewModelScope.launch(Dispatchers.IO) {
            updatePosts(data)
        }
    }

    fun getPost(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            if (getPostUseCase(id) != null)
                _data.postValue(getPostUseCase(id)!!)
        }
    }

    fun insertPost(data: DataModelItem) {
        viewModelScope.launch(Dispatchers.IO) {
            insetPostUseCase(data)
        }
    }
}