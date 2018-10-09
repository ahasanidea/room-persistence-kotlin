package com.ahasanidea.kotlin.roompersistence.data

import android.arch.persistence.room.*
import com.ahasanidea.kotlin.roompersistence.model.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM Task")
    fun getAll():List<Task>

    @Insert
    fun insert(task: Task)
    @Delete
    fun delete(task: Task)
    @Update
    fun update(task: Task)
}