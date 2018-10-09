package com.ahasanidea.kotlin.roompersistence

import android.app.Application
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.startActivity
import android.widget.Toast
import com.ahasanidea.kotlin.roompersistence.data.TaskDao
import com.ahasanidea.kotlin.roompersistence.data.TaskDatabase
import com.ahasanidea.kotlin.roompersistence.model.Task
import kotlinx.android.synthetic.main.activity_add_task.*
import java.lang.Exception

class AddTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        button_save.setOnClickListener {
            saveTask()
        }
    }

    fun saveTask() {
        if (editTextTask.text.isEmpty()) {
            editTextTask.error = "Task is required"
            editTextTask.requestFocus()
            return
        }
        if (editTextDesc.text.isEmpty()) {
            editTextDesc.error = "Desc is required"
            editTextDesc.requestFocus()
            return
        }
        if (editTextFinishBy.text.isEmpty()) {
            editTextFinishBy.error = "Finished by is required"
            editTextFinishBy.requestFocus()
            return
        }

        try {
            AsyncSaveTask(TaskDatabase.getDatabase(application).taskDao()).execute(Task(task = editTextTask.text.toString(), desc = editTextDesc.text.toString(), finishedBy = editTextFinishBy.text.toString()))
            startActivity(Intent(this, MainActivity::class.java))
            Toast.makeText(application, "Saved", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }

    class AsyncSaveTask(val dao:TaskDao) : AsyncTask<Task, Unit, Unit>() {
        override fun doInBackground(vararg params: Task) {
            //adding to database
            try {
                dao.insert(params[0])
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }
}
