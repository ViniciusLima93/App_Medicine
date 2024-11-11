package com.example.codedoctors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list_of_frequency: Spinner = findViewById(R.id.spinner_medicine_schedule)

        ArrayAdapter.createFromResource(
            this,
            R.array.options_schedules,
            android.R.layout.simple_spinner_item
        ).also { adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            list_of_frequency.adapter = adapter
        }
    }
}