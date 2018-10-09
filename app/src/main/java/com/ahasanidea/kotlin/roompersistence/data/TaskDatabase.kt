package com.ahasanidea.kotlin.roompersistence.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.ahasanidea.kotlin.roompersistence.model.Task

@Database(entities = [Task::class],version = 1)
abstract class TaskDatabase :RoomDatabase() {
    abstract fun taskDao():TaskDao
    companion object {
        private var INSTANCE: TaskDatabase? = null

        fun getDatabase(context: Context): TaskDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, TaskDatabase::class.java, "task_db").build()
            }
            return INSTANCE as TaskDatabase
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}