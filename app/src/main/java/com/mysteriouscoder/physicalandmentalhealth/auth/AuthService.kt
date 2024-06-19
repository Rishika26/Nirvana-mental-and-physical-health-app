package com.mysteriouscoder.physicalandmentalhealth.auth

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.userProfileChangeRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class AuthService(
    private val auth: FirebaseAuth = Firebase.auth,
) {
    fun login(state: MutableStateFlow<LoginState>) {
        Log.d("AuthService", "login: ${state.value.email} ${state.value.password}")
        auth.signInWithEmailAndPassword(state.value.email, state.value.password)
            .addOnFailureListener {
                state.update { state ->
                    state.copy(error = it.message ?: "An error occurred", isLoading = false)
                }
            }.addOnSuccessListener {
                state.update { state ->
                    state.copy(isLoginSuccess = true, isLoading = false, error = "")
                }
            }
    }

    fun register(state: MutableStateFlow<RegisterState>) {
        auth.createUserWithEmailAndPassword(state.value.email, state.value.password)
            .addOnFailureListener {
                state.update { state ->
                    state.copy(error = it.message ?: "An error occurred", isLoading = false)
                }
            }.addOnSuccessListener {
                state.update { state ->
                    state.copy(isRegisterSuccess = true, isLoading = false, error = "")
                }
                val profileUpdates = userProfileChangeRequest { displayName = state.value.username }
                it.user?.updateProfile(profileUpdates)
            }
    }
}