package com.ahasanidea.kotlin.roompersistence

import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ahasanidea.kotlin.roompersistence.data.TaskDao
import com.ahasanidea.kotlin.roompersistence.data.TaskDatabase
import com.ahasanidea.kotlin.roompersistence.model.Task

import kotlinx.android.synthetic.main.activity_update_task.*
import java.lang.Exception

class UpdateTaskActivity : AppCompatActivity() {
    private var task: Task?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_task)

        task= intent.extras["task"] as Task?
        loadTask(task)
        button_update.setOnClickListener{
            updateTask()
        }
        button_delete.setOnClickListener{
            deleteTask()
        }
    }

    private fun deleteTask() {
        try {
            AsyncDeleteTask(TaskDatabase.getDatabase(application).taskDao()).execute(task)
            startActivity(Intent(this, MainActivity::class.java))
            Toast.makeText(application, "deleted", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    private fun loadTask(task: Task?) {
        editTextTask.setText(task!!.task)
        editTextDesc.setText(task!!.desc)
        editTextFinishBy.setText(task!!.finishedBy)
        checkBoxFinished.isChecked=(task!!.finished)
    }
    fun updateTask() {
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
            AsyncUpdateTask(TaskDatabase.getDatabase(application).taskDao()).execute(Task(task = editTextTask.text.toString(), desc = editTextDesc.text.toString(), finishedBy = editTextFinishBy.text.toString(),finished = checkBoxFinished.isChecked))
            startActivity(Intent(this, MainActivity::class.java))
            Toast.makeText(application, "Updated", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }
    class AsyncDeleteTask(val dao: TaskDao) : AsyncTask<Task, Unit, Unit>() {
        override fun doInBackground(vararg params: Task) {
            //adding to database
            try {
                dao.delete(params[0])
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }
    class AsyncUpdateTask(val dao: TaskDao) : AsyncTask<Task, Unit, Unit>() {
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
