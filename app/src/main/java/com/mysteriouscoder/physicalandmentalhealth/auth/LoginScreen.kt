package com.mysteriouscoder.physicalandmentalhealth.auth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mysteriouscoder.physicalandmentalhealth.App_icon_component
import com.mysteriouscoder.physicalandmentalhealth.GIFLoading
import com.mysteriouscoder.physicalandmentalhealth.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    state: LoginState = LoginState(),
    onEvent: (LoginEvent) -> Unit = {},
    onNavigateToRegister: () -> Unit = {},
    onNavigateToHome: () -> Unit = {},
) {

    val passwordVisible = remember {
        mutableStateOf(false)
    }

    val coroutineScope = rememberCoroutineScope()
    val isLoading = remember {
        mutableStateOf(false)
    }


    if (state.isLoginSuccess) {
        onNavigateToHome()
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.onPrimary,
                            MaterialTheme.colorScheme.primaryContainer
                        )
                    )
                )
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(280.dp)
        ) {

            App_icon_component()
            Text(
                text = "Nirvana",
                style = MaterialTheme.typography.headlineLarge,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold
            )

            AnimatedVisibility(visible = state.error.isNotBlank()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.extraSmall
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = state.error,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        IconButton(
                            onClick = { onEvent(LoginEvent.ClearError) },
                            modifier = Modifier.size(18.dp)
                        ) {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = "close",
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                }
            }
            OutlinedTextField(
                value = state.email,
                onValueChange = { onEvent(LoginEvent.SetEmail(it)) },
                placeholder = { Text("Email") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "email",
                        modifier = Modifier
                            .padding(start = 24.dp)
                            .padding(end = 8.dp),
                    )
                },
                label = {
                    Text(
                        text = "Email",
                    )
                },
                singleLine = true,
                shape = MaterialTheme.shapes.extraLarge,

                )
            OutlinedTextField(
                value = state.password,
                onValueChange = { onEvent(LoginEvent.SetPassword(it)) },
                placeholder = { Text("Password") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = "password",
                        modifier = Modifier
                            .padding(start = 24.dp)
                            .padding(end = 8.dp),
                    )
                },
                label = {
                    Text(
                        text = "Password",
                    )
                },
                singleLine = true,
                shape = MaterialTheme.shapes.extraLarge,
                trailingIcon = {


                    val iconImage = if (passwordVisible.value) {
                        Icons.Filled.Visibility
                    } else {
                        Icons.Filled.VisibilityOff
                    }

                    var description = if (passwordVisible.value) {
                        stringResource(id = R.string.hidepassword)
                    } else {
                        stringResource(id = R.string.showpassword)
                    }

                    IconButton(onClick = {
                        passwordVisible.value = !passwordVisible.value
                    }) {

                        Icon(
                            imageVector = iconImage,
                            contentDescription = description
                        )
                    }

                },
                visualTransformation =


                if (passwordVisible.value) VisualTransformation.None
                else
                    PasswordVisualTransformation()

            )
            ElevatedButton(
                onClick = {
                    isLoading.value = true
                    coroutineScope.launch {
                        delay(1700L) // delay for 1.7 seconds
                        isLoading.value = false
                    }
                    onEvent(LoginEvent.OnLogin)
                },
                modifier = Modifier
//                    .align(Alignment.Start)
                    .width(120.dp),
                shape = MaterialTheme.shapes.extraLarge,
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                enabled = !state.isLoading
            ) {
                when {
                    state.isLoading -> {
                        Icon(Icons.Default.Refresh, contentDescription = "Loading")
                    }

                    else -> {
                        Text(text = "Login")
                    }
                }
            }


            Row {
                Text(text = "Don't have an Account?")
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Register",
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.clickable { onNavigateToRegister() },
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }


        if (isLoading.value) {
            GIFLoading(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .height(170.dp)
                    .width(200.dp)
                    .padding(bottom = 20.dp),
                gif=R.drawable.loadinggif
            )
        }


    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    LoginScreen()
}