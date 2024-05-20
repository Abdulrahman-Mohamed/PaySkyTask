package com.example.payskytask.appFeature.data.data_source

import androidx.lifecycle.LiveData
import com.example.payskytask.appFeature.data.model.DataModelItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("posts")
    suspend fun fetchData(): Response<List<DataModelItem>>

    @POST("posts")
    suspend fun insertItem(@Body item: DataModelItem): Response<DataModelItem>

    @PUT("posts/{id}")
    suspend fun updateItem(@Path("id") id: Int, @Body item: DataModelItem): Response<DataModelItem>

    @DELETE("posts/{id}")
    suspend fun deleteItem(@Path("id") id: Int): Response<Unit>
}