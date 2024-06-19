package com.mysteriouscoder.physicalandmentalhealth

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.mysteriouscoder.physicalandmentalhealth.auth.AuthActivity
import com.mysteriouscoder.physicalandmentalhealth.ui.screens.onboarding.OnBoardingScreen
import com.mysteriouscoder.physicalandmentalhealth.ui.theme.PhysicalAndMentalHealthTheme


class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false)

        installSplashScreen()
        auth = Firebase.auth
        db = Firebase.firestore
        setContent {
            PhysicalAndMentalHealthTheme {


                    OnBoardingScreen(
                        auth = auth,
                        db = db
                    )
//                BottomNavigation(viewModelDialog,auth,db)
//
            }
        }
    }
    override fun onStart() {
        super.onStart()
        if (auth.currentUser == null) {
            val i = Intent(this, AuthActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}

