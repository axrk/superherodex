package com.example.vancouver_transport

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.SearchEvent
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import android.widget.ArrayAdapter

import android.widget.ListView
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchView = findViewById<SearchView>(R.id.searchView)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewSearch)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                val adapter = recyclerView.adapter as SuperHeroAdapter
                if (p0 != null) {
                    adapter.filter(p0)
                }

                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })
    }
}