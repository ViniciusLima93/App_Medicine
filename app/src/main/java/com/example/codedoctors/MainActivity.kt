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
import com.google.android.material.tabs.TabLayout
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore


class MainActivity : AppCompatActivity() {


    private lateinit var frameLayout : FrameLayout;

    private lateinit var tabLayout: TabLayout;


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        frameLayout = findViewById(R.id.frameLayout)

        tabLayout = findViewById((R.id.table_layout))

        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, FirstFragment())
            .addToBackStack(null)
            .commit();

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {

            var fragment:Fragment = FirstFragment()
            override fun onTabReselected(tab: TabLayout.Tab) {

            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                when(tab.position) {
                    0 -> fragment  = FirstFragment()
                    1 ->  fragment = SecondFragment()
                    2  ->  fragment = ThirdFragment()

                }
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }


        }        )}

    }
