package com.example.stickynotesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() ,INoteRVAdapter{

    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
//        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.layoutManager= GridLayoutManager(this,2)
        val adapter=NoteRVAdapter(this,this)
        recyclerView.adapter=adapter

        viewModel =ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {list->
            list?.let {
                adapter.updateList(it)
            }

        })
    }

    override fun onItemClicked(note: Note) {
        viewModel.deleteNote(note)
        //Toast.makeText(this,"${note.text} is Deleted",Toast.LENGTH_LONG).show()
    }

    fun submitData(view: View) {
        val noteText=input.text.toString()
        if(noteText.isNotEmpty()){
            viewModel.insertNote(Note(noteText))
            //Toast.makeText(this,"${noteText} is added",Toast.LENGTH_LONG).show()
        }

    }

}