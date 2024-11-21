package com.example.codedoctors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CheckBox

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }


    fun SelectedHardPain () {
        findViewById<CheckBox>(R.id.check_inc)
            .setOnCheckedChangeListener { buttonView, isChecked ->
                Log.d("CHECKBOXES", "selected => $isChecked")
            }
    }


}