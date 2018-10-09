package com.ahasanidea.kotlin.roompersistence

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem

import com.ahasanidea.kotlin.roompersistence.adapter.TaskAdapter
import com.ahasanidea.kotlin.roompersistence.data.TaskDatabase
import com.ahasanidea.kotlin.roompersistence.model.Task


import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            startActivity(Intent(this, AddTaskActivity::class.java))
        }

       AsyncGetTasks(recyclerview_tasks,this,application).execute()
    }

     class AsyncGetTasks(val recyclerView: RecyclerView,val ctx:Context,val app:Application): AsyncTask<Void, Void, List<Task>>() {
        override fun doInBackground(vararg voids:Void):List<Task> {
            var  taskList:List<Task>?=null
            try {
                 taskList = TaskDatabase.getDatabase(app)
                        .taskDao()
                        .getAll()

            }catch (e:Exception){
                e.printStackTrace()
            }
            return taskList!!
        }
        override fun onPostExecute(tasks:List<Task>) {
            super.onPostExecute(tasks)
            val adapter = TaskAdapter(tasks as ArrayList<Task>)
            recyclerView.layoutManager=LinearLayoutManager(ctx)
            recyclerView.adapter=adapter
        }
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
