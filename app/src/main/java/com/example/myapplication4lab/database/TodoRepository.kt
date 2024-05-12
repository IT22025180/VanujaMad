package com.example.myapplication4lab.database

class TodoRepository(private val db:TodoDatabase) {
    suspend fun insert(todo: Todo) = db.getTodoDao().insert(todo)
    suspend fun delete(todo: Todo) = db.getTodoDao().delete(todo)
    suspend fun update(id:Int , newValue:String) = db.getTodoDao().update(id,newValue)
    fun getAllTodoItems():List<Todo> = db.getTodoDao().getAllTodoItems()
}