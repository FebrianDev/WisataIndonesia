package com.febrian.wisataindonesia.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DataDao {
    @Insert
    fun insert(news: DataRoom)

    @Query("DELETE from data where name = :name")
    fun delete(name: String)

    @Query("SELECT * from data order by id asc")
    fun getAllData(): List<DataRoom>

    @Query("SELECT * from data where id = :myId ")
    fun getDataById(myId: Int): DataRoom

    @Query("SELECT * from data where name = :name")
    fun getDataByTitle(name: String): DataRoom

    @Query("SELECT EXISTS(SELECT * from data where name = :name)")
    fun dataExist(name: String): Boolean

    @Query("SELECT EXISTS(SELECT * from data where id = :id)")
    fun dataExist(id: Int): Boolean
}
