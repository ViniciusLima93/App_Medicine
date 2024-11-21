package com.example.codedoctors

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.fragment.app.commitNow
import com.example.codedoctors.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var  binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonSend.setOnClickListener(this)


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

            val medicine =  hashMapOf<String,Object>()
                "nome" to name_medicine
                "dosagem" to dose
                "frequencia" to freq
                "comentarios" to comments

            database.collection("medicines").add(medicine).addOnSuccessListener { documentReference ->
                Log.d(TAG,"DocumentSnapshot added with ID: ${documentReference.id}")
                Toast.makeText(this,"Dados Salvos com Sucesso", Toast.LENGTH_SHORT).show()


            }
                .addOnFailureListener { e:Throwable? ->
                    Log.w(TAG, "Error adding documents", e)
                    Toast.makeText(this, "Ocorreu um erro ao Salvar dados", Toast.LENGTH_SHORT).show()
                }

        }


    }

//    fun goToNext() {
//        val fragment = FirstFragment()
//
//        val fragmentManager = supportFragmentManager
//
//        val transaction = fragmentManager.beginTransaction()
//        transaction.replace(R.id.frameLayout, fragment)
//        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_MATCH_ACTIVITY_OPEN)
//            .addToBackStack(null)
//            .commit(setRe)
//    }



    override fun onClick(view: View?) {

        if (view !=null) {
            if (view.id == R.id.button_send) {
                var name_medicine = binding.editNameMedicine.text.toString()
                var dose = binding.editDose.text.toString()
                var freq = binding.spinnerMedicineSchedule.selectedItem.toString()
                var comments = binding.textArea.text.toString()

                saveMedicine(name_medicine, dose, freq, comments)
//                supportFragmentManager.beginTransaction().replace(R.id.mainLayout, FirstFragment())
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
                  val intent = Intent(this,MainActivity2::class.java)
                    startActivity(intent)

            }
        }
        Log.d("medicine data  =>", "name"+ " " + binding.editNameMedicine.text.toString() + "" + "dose" +  "" + binding.editDose.text.toString() +
                "" + "freq" + binding.spinnerMedicineSchedule.selectedItem.toString() + "comments"+ "" + binding.textArea.text.toString())

    }
}