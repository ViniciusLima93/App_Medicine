package com.example.codedoctors

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.codedoctors.databinding.ActivityMain2Binding
import com.example.codedoctors.databinding.FragmentThirdBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObjects

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ThirdFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

data class MedicineClass (
    val nome: String? = null,
    val frequencia: String? = null,
    val dosagem:String? = null,
    val comentarios: String? = null
)

data class  PainClass (
    val name_symptoms:String? = null,
    val pain_fell:List<String>? = null
)

class MedicineViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    val name: TextView = itemView.findViewById(R.id.list_name)
    val frequencia: TextView = itemView.findViewById(R.id.list_frequencia)
    val dosagem: TextView = itemView.findViewById(R.id.list_dosagem)
    val comentarios: TextView = itemView.findViewById(R.id.list_comentarios)
}

class MedicineAdapter (private val medicines: List<MedicineClass>) :
    RecyclerView.Adapter<MedicineViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_medicine, parent, false)
        return MedicineViewHolder(view)
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        val medicine = medicines[position]
        holder.name.text = medicine.nome
        holder.frequencia.text = medicine.frequencia
        holder.dosagem.text = medicine.dosagem
        holder.comentarios.text = medicine.comentarios
    }

    override fun getItemCount(): Int = medicines.size
}


class ThirdFragment : Fragment() {

    private lateinit var binding: FragmentThirdBinding

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentThirdBinding.inflate(layoutInflater)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_third, container, false)

//        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_medicines)

        val db = Firebase.firestore

        val docRef = db.collection("medicines")
        val docRefSymptons = db.collection("symptoms")

        val medicines = mutableListOf<MedicineClass>()

        val syntoms = mutableListOf<PainClass>()

            docRefSymptons.get().addOnSuccessListener { documentSnapshot ->
                val symptons = documentSnapshot.toObjects(PainClass::class.java)

                val nameSymptons = symptons.map { v -> v.name_symptoms}
                binding.painViewFromFirebase.text = nameSymptons.joinToString ("\n")

                Log.d(TAG, "THIS IS symptoms REGISTER => $symptons")
            }

            docRef.get().addOnSuccessListener{documentSnapshot ->
            val medicine = documentSnapshot.toObjects(MedicineClass::class.java)

                val coments = medicine.map { v -> v.comentarios }
                binding.commentsViewFromFirebase.text = coments.joinToString("\n")

                val name_medicine = medicine.map {v -> v.nome}
                binding.medicineViewFromFirebase.text = name_medicine.joinToString("\n")

                Log.d(TAG, "THIS IS MEDICINES REGISTER => $medicine")



            }



        // Inflate the layout for this fragment
        return binding.root


        }





    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ThirdFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ThirdFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}