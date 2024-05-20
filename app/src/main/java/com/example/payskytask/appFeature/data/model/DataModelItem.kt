package com.example.payskytask.appFeature.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data_table")
data class DataModelItem(
    val body: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val userId: Int,
    var isSynched: Boolean = true,
    var isDeleted: Boolean = false
)