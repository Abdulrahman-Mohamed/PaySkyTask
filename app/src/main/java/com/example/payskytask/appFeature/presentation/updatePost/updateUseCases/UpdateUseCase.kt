package com.example.payskytask.appFeature.presentation.updatePost.updateUseCases

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.payskytask.appFeature.data.model.DataModelItem
import com.example.payskytask.appFeature.data.repository.RepositoryIMP
import com.example.payskytask.appFeature.domain.UTILS.Result
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UpdateUseCase @Inject constructor(
    @ApplicationContext val context: Context,
    val repo: RepositoryIMP
) {
    suspend operator fun invoke(model: DataModelItem) {

        repo.updateItem(model)

    }
}