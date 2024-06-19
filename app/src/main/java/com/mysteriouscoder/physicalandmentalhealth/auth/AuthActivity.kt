package com.mysteriouscoder.physicalandmentalhealth.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.mysteriouscoder.physicalandmentalhealth.MainActivity
import com.mysteriouscoder.physicalandmentalhealth.ui.theme.PhysicalAndMentalHealthTheme

class AuthActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        db = Firebase.firestore
        installSplashScreen()

        setContent {
            PhysicalAndMentalHealthTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Scaffold(
//
//                        topBar = {
//                            TopAppBar(
//                                title = {
//                                    Text(
//                                        text = "\tNirvana",
//                                        color = Color.White,
//                                        fontFamily = FontFamily(Font(R.font.nunito_extrabold)),
//
//                                        )
//                                },
//                                colors = TopAppBarDefaults.topAppBarColors(
//                                    containerColor = MaterialTheme.colorScheme.primary,
//                                )
//                            )
//                        }) {it
                        AuthNavigation()
//                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null) {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}