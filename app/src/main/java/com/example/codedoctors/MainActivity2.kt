package com.example.codedoctors

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.codedoctors.databinding.ActivityMain2Binding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore


class MainActivity2 : AppCompatActivity(), View.OnClickListener {

    private lateinit var  binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backToListSymptoms.setOnClickListener(this)
        binding.buttonSaveSymptoms.setOnClickListener(this)


    }


    fun SelectedHardPain(): Array<String> {
        val listPain = mutableListOf<String>()

        val checkBoxIds = intArrayOf(R.id.check_inc, R.id.check_partial, R.id.check_moderate)

        for (checkboxId in checkBoxIds) {
            val checkbox = findViewById<CheckBox>(checkboxId)
            if (checkbox.isChecked) {
                listPain.add(checkbox.text.toString())
            }
        }

        return listPain.toTypedArray()
    }


    private fun logCheckboxState(tag: String, checkboxName: String, isChecked: Boolean) {
        val state = if (isChecked) "checked" else "unchecked"
        Log.d(tag, "$checkboxName is $state")
    }

    private fun isValid(): Boolean {
        return (
                binding.editFeels.text.toString() != ""
                )
    }

    @SuppressLint("SuspiciousIndentation")
    private fun saveSynptoms (name_symptoms: String, pain_fell: List<String>) {
        val database = Firebase.firestore

        if (isValid()) {

            val symptoms = hashMapOf(
                "name_symptoms" to name_symptoms,
                "pain_fell" to pain_fell
            )

            database.collection("symptoms").add(symptoms).addOnSuccessListener { documentReference ->
                Log.d(TAG,"Documentsnapshot add with ID: ${documentReference.id}")
                Toast.makeText(this, "Dados Salvos com Sucesso", Toast.LENGTH_SHORT).show()
                finish()

//                val navController = findNavController(R.id.AcitivityMains2_FramLayout)
//                navController.navigate(R.id.list_of_Symptoms)

//                val navController = Activity().findNavController(R.id.AcitivityMains2_FramLayout)
//
//                    navController.navigate(R.id.action_secondFragment_to_mainActivity2)


            }
                .addOnFailureListener {e:Throwable? ->
                    Log.w(TAG, "Error adding documents", e)
                    Toast.makeText(this, "Ocorreu um erro ao Salvar dados", Toast.LENGTH_SHORT).show()

                }

        }


    }


    override  fun onClick(view: View?) {
        if (view != null ) {
            if (view.id == R.id.back_To_ListSymptoms) {
//                val navController = findNavController(R.id.AcitivityMains2_FramLayout)
//                navController.navigate(R.id.list_of_Symptoms)
                finish()
            }

            if (view.id == R.id.button_SaveSymptoms) {
                var name_symptoms = binding.editFeels.text.toString()
                val pains = SelectedHardPain()
                val list_of_pains = pains.toList()
                saveSynptoms(name_symptoms,list_of_pains)

            }
        }
    }





}