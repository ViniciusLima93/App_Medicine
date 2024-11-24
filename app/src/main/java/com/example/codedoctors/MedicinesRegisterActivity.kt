package com.example.codedoctors

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.content.ContentValues.TAG
import android.content.Intent
import android.widget.Spinner
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.codedoctors.databinding.ActivityMedicinesRegisterBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore



class MedicinesRegisterActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var  binding: ActivityMedicinesRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMedicinesRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonSend.setOnClickListener(this)
        binding.buttonBackToListMedicines.setOnClickListener(this)


        val list_of_frequency: Spinner = findViewById(R.id.spinner_medicine_schedule)

        ArrayAdapter.createFromResource(
            this,
            R.array.options_schedules,
            android.R.layout.simple_spinner_item
        ).also { adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            list_of_frequency.adapter = adapter
        }
    }

    private fun isValid(): Boolean {
        return (
                binding.editNameMedicine.text.toString() != "" &&
                        binding.editDose.text.toString() != "" &&
                        binding.textArea.text.toString().length >= 3
                )
    }

    @SuppressLint("SuspiciousIndentation")
    private fun saveMedicine (name_medicine: String, dose:String, freq:String, comments:String) {


        val database = Firebase.firestore

        if (isValid()) {

            val medicine =  hashMapOf(
                "nome" to name_medicine,
                "dosagem" to dose,
                "frequencia" to freq,
                "comentarios" to comments
            )


            database.collection("medicines").add(medicine).addOnSuccessListener { documentReference ->
                Log.d(TAG,"DocumentSnapshot added with ID: ${documentReference.id}")
                Toast.makeText(this,"Dados Salvos com Sucesso", Toast.LENGTH_SHORT).show()

//                val intent = Intent(this,MainActivity2::class.java)
//                startActivity(intent)


            }
                .addOnFailureListener { e:Throwable? ->
                    Log.w(TAG, "Error adding documents", e)
                    Toast.makeText(this, "Ocorreu um erro ao Salvar dados", Toast.LENGTH_SHORT).show()
                }

        }


    }


    @SuppressLint("SuspiciousIndentation")
    override fun onClick(view: View?) {

        if (view !=null) {
            if (view.id == R.id.button_send) {
                var name_medicine = binding.editNameMedicine.text.toString()
                var dose = binding.editDose.text.toString()
                var freq = binding.spinnerMedicineSchedule.selectedItem.toString()
                var comments = binding.textArea.text.toString()

                saveMedicine(name_medicine, dose, freq, comments)


            }

            if(view.id == R.id.button_back_To_ListMedicines) {
                val intent = Intent(this,FirstFragment::class.java)
                startActivity(intent)
            }

        }
        Log.d("medicine data  =>", "name"+ " " + binding.editNameMedicine.text.toString() + "" + "dose" +  "" + binding.editDose.text.toString() +
                "" + "freq" + binding.spinnerMedicineSchedule.selectedItem.toString() + "comments"+ "" + binding.textArea.text.toString())



    }



}









