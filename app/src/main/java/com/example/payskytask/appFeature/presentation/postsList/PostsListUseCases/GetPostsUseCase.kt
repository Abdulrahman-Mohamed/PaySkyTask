package com.example.payskytask.appFeature.presentation.postsList.PostsListUseCases

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.payskytask.appFeature.data.model.DataModelItem
import com.example.payskytask.appFeature.data.repository.RepositoryIMP
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import com.example.payskytask.appFeature.domain.UTILS.Result

class GetPostsUseCase @Inject constructor(
    @ApplicationContext val context: Context,
    val repo: RepositoryIMP
) {
    suspend operator fun invoke(): LiveData<List<DataModelItem>>? {
        var data: LiveData<List<DataModelItem>>? = null
        when (val list: Result<LiveData<List<DataModelItem>>> = repo.fetchData()) {
            is Result.Success -> {
                data = list.data
            }

            is Result.ErrorWithResult -> {
                data = list.data
            }

            is Result.Error -> {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }

            else -> {}
        }

        return data

    }
}
