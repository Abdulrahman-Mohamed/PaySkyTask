package com.example.payskytask.appFeature.data.data_source

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.payskytask.appFeature.data.model.DataModelItem
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DataModelItem)

    @Update
    suspend fun update(item: DataModelItem)

    @Query("Delete FROM DATA_TABLE WHERE id =:itemID")
    suspend fun delete(itemID: Int)

    @Query("SELECT * FROM DATA_TABLE WHERE isDeleted = false")
    fun getAllItems(): LiveData<List<DataModelItem>>

    @Query("SELECT * FROM DATA_TABLE WHERE id =:id ")
    fun getItem(id: Int): DataModelItem?

    @Query("SELECT * FROM DATA_TABLE WHERE isSynched = false")
    suspend fun getUnsyncedItems(): List<DataModelItem>
}