package com.ahasanidea.kotlin.roompersistence

import android.app.Application
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ahasanidea.kotlin.roompersistence.data.TaskDatabase
import com.ahasanidea.kotlin.roompersistence.model.Task
import kotlinx.android.synthetic.main.activity_add_task.*

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
        AsyncSaveTask(Task(task = editTextTask.text.toString(), desc = editTextDesc.text.toString(), finishedBy = editTextFinishBy.text.toString()),application).execute()
        startActivity(Intent(this, MainActivity::class.java))


    }

    class AsyncSaveTask(val task: Task,val application: Application) : AsyncTask<Void, Void, Void>() {
         override fun doInBackground(vararg voids: Void): Void? {
            //adding to database
            TaskDatabase.getDatabase(application).taskDao().insert(task)

            return null
        }

         override fun onPostExecute(aVoid: Void) {
            super.onPostExecute(aVoid)
             Toast.makeText(application, "Saved", Toast.LENGTH_LONG).show()
        }
    }
}
