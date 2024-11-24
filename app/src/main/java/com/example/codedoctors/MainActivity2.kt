package com.example.codedoctors

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import com.example.codedoctors.databinding.ActivityMain2Binding


class MainActivity2 : AppCompatActivity(), View.OnClickListener {

    private lateinit var  binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backToListSymptoms.setOnClickListener(this)

    }


    fun SelectedHardPain () {
        findViewById<CheckBox>(R.id.check_inc)
            .setOnCheckedChangeListener { buttonView, isChecked ->
                Log.d("CHECKBOXES", "selected => $isChecked")
            }
    }

    override  fun onClick(view: View?) {
        if (view != null ) {
            if (view.id == R.id.back_To_ListSymptoms) {
                val intent =  Intent(this, SecondFragment::class.java)
                startActivity(intent)
            }
        }
    }




}