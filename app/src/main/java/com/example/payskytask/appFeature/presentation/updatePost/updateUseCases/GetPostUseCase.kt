package com.example.payskytask.appFeature.presentation.updatePost.updateUseCases

import android.content.Context
import com.example.payskytask.appFeature.data.model.DataModelItem
import com.example.payskytask.appFeature.data.repository.RepositoryIMP
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GetPostUseCase @Inject constructor(
    @ApplicationContext val context: Context,
    val repo: RepositoryIMP
) {
    suspend operator fun invoke(id: Int): DataModelItem? {

        return repo.getPost(id)

    }
}