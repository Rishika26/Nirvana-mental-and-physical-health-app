package com.mysteriouscoder.physicalandmentalhealth.auth

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mysteriouscoder.physicalandmentalhealth.MainActivity

enum class Screen(val route: String) {
    Login("login"),
    Register("register"),
}

fun moveToMain(context: Context) {
    val i = Intent(context, MainActivity::class.java)
    context.startActivity(i)
}

@Composable
fun AuthNavigation() {
    val navController = rememberNavController()
    val vm: AuthViewModel = viewModel()
    val context = LocalContext.current
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            LoginScreen(
                state = vm.loginState.collectAsState().value,
                onEvent = vm::onLoginEvent,
                onNavigateToRegister = {
                    navController.navigate(Screen.Register.route)
                },
                onNavigateToHome = {
                    moveToMain(context)
                })
        }
        composable(Screen.Register.route) {

            RegisterScreen(
                state = vm.registerState.collectAsState().value,
                onEvent = vm::onRegisterEvent,
                onBack = {
                    navController.popBackStack()
                },
                onNavigateToHome = {
                    moveToMain(context)
                })
        }
    }
}