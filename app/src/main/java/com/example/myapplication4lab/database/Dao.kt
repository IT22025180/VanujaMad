package com.example.myapplication4lab.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface Dao {

    @Insert
    suspend fun insert(todo: Todo)


    @Delete
    suspend fun delete(todo: Todo)

    @Query("SELECT * FROM Todo")
    fun getAllTodoItems():List<Todo>

    @Query("SELECT * FROM Todo WHERE id =:id")
    fun getOne(id:Int):Todo

    @Update
    suspend fun update(todo: Todo)

    @Query("UPDATE Todo SET item = :newValue WHERE id = :id")
    suspend fun update(id: Int?, newValue: String)

}