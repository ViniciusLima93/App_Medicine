package com.example.codedoctors

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.codedoctors.ui.theme.CodeDoctorsTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class AuthAndLogin : ComponentActivity() {
    private lateinit  var auth: FirebaseAuth
    private val email = "vnlima9@gmail.com"
    private val password = "123456"
   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
           task -> if (task.isSuccessful) {
           setContent {
               CodeDoctorsTheme {
                   // A surface container using the 'background' color from the theme
                   Surface(
                       modifier = Modifier.fillMaxSize(),
                       color = MaterialTheme.colorScheme.background
                   ) {
                       Greeting(name = email, modifier = Modifier.fillMaxSize())
                   }
               }
           }
           }

       }

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CodeDoctorsTheme {
        Greeting("Android")
    }
}