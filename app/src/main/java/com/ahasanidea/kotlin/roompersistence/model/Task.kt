package com.ahasanidea.kotlin.roompersistence.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable
@Entity
data class Task(@PrimaryKey(autoGenerate = true) var id:Int?=null, var task:String, var desc:String, var finishedBy:String, var finished:Boolean=false ) :Serializable