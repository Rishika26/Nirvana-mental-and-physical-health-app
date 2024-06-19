package com.mysteriouscoder.physicalandmentalhealth.auth

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String = "",
    val isLoginSuccess: Boolean = false,
)

data class RegisterState(
    val email: String = "",
    val password: String = "",
    val username: String = "",
    val confirmPassword: String = "",
    var isLoading: Boolean = false,
    var error: String = "",
    var isRegisterSuccess: Boolean = false,
)

sealed class RegisterEvent {
    data class SetUsername(val username: String) : RegisterEvent()
    data class SetEmail(val email: String) : RegisterEvent()
    data class SetPassword(val password: String) : RegisterEvent()
    data class SetConfirmPassword(val confirmPassword: String) : RegisterEvent()
    data object OnSaveUser : RegisterEvent()
    data object ClearError : RegisterEvent()
}

sealed class LoginEvent {
    data class SetEmail(val email: String) : LoginEvent()
    data class SetPassword(val password: String) : LoginEvent()
    data object OnLogin : LoginEvent()
    data object ClearError : LoginEvent()
}

class AuthViewModel(
    private val authService: AuthService = AuthService()
) : ViewModel() {
    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()
    private val _registerState = MutableStateFlow(RegisterState())
    val registerState = _registerState.asStateFlow()

    fun onLoginEvent(event: LoginEvent) {
        when (event) {
            LoginEvent.OnLogin -> {
                try {

                    authService.login(_loginState)
                } catch (e: Exception) {
                    _loginState.update { state ->
                        state.copy(error = e.message ?: "An error occurred")
                    }
                }
            }

            is LoginEvent.SetEmail -> {
                _loginState.update { state ->
                    state.copy(email = event.email)
                }
            }

            is LoginEvent.SetPassword -> {
                _loginState.update { state ->
                    state.copy(password = event.password)
                }
            }

            LoginEvent.ClearError -> {
                _loginState.update { state ->
                    state.copy(error = "")
                }
            }
        }
    }

    fun onRegisterEvent(event: RegisterEvent) {
        when (event) {
            RegisterEvent.OnSaveUser -> {
                try {
                    authService.register(_registerState)
                } catch (e: Exception) {
                    _registerState.update {
                        it.copy(error = e.message ?: "Some error occurred")
                    }
                }
            }

            is RegisterEvent.SetConfirmPassword -> {
                _registerState.update { it.copy(confirmPassword = event.confirmPassword) }
            }

            is RegisterEvent.SetEmail -> {
                _registerState.update { it.copy(email = event.email) }
            }

            is RegisterEvent.SetPassword -> {
                _registerState.update { it.copy(password = event.password) }
            }

            is RegisterEvent.SetUsername -> {
                _registerState.update { it.copy(username = event.username) }
            }

            RegisterEvent.ClearError -> {
                _registerState.update { state ->
                    state.copy(error = "")
                }
            }
        }
    }
}