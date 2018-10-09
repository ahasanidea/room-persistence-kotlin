package com.ahasanidea.kotlin.roompersistence.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ahasanidea.kotlin.roompersistence.R
import com.ahasanidea.kotlin.roompersistence.model.Task

class TaskAdapter( val taskList:ArrayList<Task>):RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter.TaskViewHolder {
        return TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_item,parent,false))
    }

    override fun getItemCount(): Int {
        return taskList.count()
    }

    override fun onBindViewHolder(holder: TaskAdapter.TaskViewHolder, position: Int) {
        holder.setTask(taskList,position)
    }
    class TaskViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        fun setTask(taskList: ArrayList<Task>, position: Int) {
            itemView.findViewById<TextView>(R.id.textViewTask).text=taskList[position].task
            itemView.findViewById<TextView>(R.id.textViewDesc).text=taskList[position].desc
            itemView.findViewById<TextView>(R.id.textViewFinishBy).text=taskList[position].finishedBy
            if (taskList[position].finished)
                itemView.findViewById<TextView>(R.id.textViewStatus).text="Completed"
            else
                itemView.findViewById<TextView>(R.id.textViewStatus).text="Not Completed"
        }


    }
}