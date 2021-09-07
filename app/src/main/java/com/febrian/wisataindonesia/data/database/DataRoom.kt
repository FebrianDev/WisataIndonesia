package com.febrian.wisataindonesia.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="data")
data class DataRoom (
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id") var id: Int = 0,
    @ColumnInfo(name = "name")
    val name : String?,
    @ColumnInfo(name = "description")
    val description : String?,
    @ColumnInfo(name = "address")
    val address: String?,
    @ColumnInfo(name = "website")
    val website : String?,
    @ColumnInfo(name = "tag")
    val tag : String?,
    @ColumnInfo(name="img")
    val img : Int?
)