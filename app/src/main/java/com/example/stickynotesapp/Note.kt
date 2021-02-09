package com.example.stickynotesapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
class Note (@ColumnInfo (name =  "text") val text:String){
    @PrimaryKey (autoGenerate = true) var Id=0  //auto increement of id

}
