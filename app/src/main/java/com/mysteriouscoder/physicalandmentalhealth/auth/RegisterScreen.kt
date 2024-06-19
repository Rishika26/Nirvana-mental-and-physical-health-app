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
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mysteriouscoder.physicalandmentalhealth.R

@Composable
fun RegisterScreen(
    state: RegisterState = RegisterState(),
    onEvent: (RegisterEvent) -> Unit = {},
    onBack: () -> Unit = {},
    onNavigateToHome: () -> Unit = {}
) {
    if (state.isRegisterSuccess) {
        onNavigateToHome()

    }
    val passwordVisible = remember {
        mutableStateOf(false)
    }
    val cpasswordVisible = remember {
        mutableStateOf(false)
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
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.width(280.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
                Icon(
                    imageVector = Icons.Default.ArrowDownward,
                    contentDescription = "Back Button",
                    modifier = Modifier
                        .rotate(90f)
                        .padding(vertical = 30.dp)
                        .clickable { onBack() }
                        .align(Alignment.Start)
                        .size(32.dp)
                )

            Text(
                text = "Let's Get Started!",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier.run { padding(bottom = 5.dp) }
            )
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Create an account on Nirvana",
                fontWeight = FontWeight.Medium,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 30.dp),
                fontSize = 14.sp

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
                            onClick = { onEvent(RegisterEvent.ClearError) },
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
                onValueChange = { onEvent(RegisterEvent.SetEmail(it)) },
                label = { Text("Email") },
                placeholder = { Text("Email") },
                leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null,
                    modifier = Modifier
                        .padding(start = 24.dp)
                        .padding(end = 8.dp),) },
                singleLine = true,
                shape = MaterialTheme.shapes.extraLarge,

            )
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = state.username,
                onValueChange = { onEvent(RegisterEvent.SetUsername(it)) },
                placeholder = { Text("Username") },
                label = { Text("Username") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 24.dp)
                            .padding(end = 8.dp),
                    )

                },
                singleLine = true,
                shape = MaterialTheme.shapes.extraLarge,

            )
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = state.password,
                onValueChange = { onEvent(RegisterEvent.SetPassword(it)) },
                label = { Text("Password") },
                placeholder = { Text("Password") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 24.dp)
                            .padding(end = 8.dp),
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
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = state.confirmPassword,
                onValueChange = { onEvent(RegisterEvent.SetConfirmPassword(it)) },
                label = { Text("Confirm Pass") },
                placeholder = { Text("Confirm Pass") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 24.dp)
                            .padding(end = 8.dp),
                    )
                },
                singleLine = true,
                shape = MaterialTheme.shapes.extraLarge,
                trailingIcon = {
                    val iconImage = if (cpasswordVisible.value) {
                        Icons.Filled.Visibility
                    } else {
                        Icons.Filled.VisibilityOff
                    }

                    var description = if (cpasswordVisible.value) {
                        stringResource(id = R.string.hidepassword)
                    } else {
                        stringResource(id = R.string.showpassword)
                    }

                    IconButton(onClick = {
                        cpasswordVisible.value = !cpasswordVisible.value
                    }) {

                        Icon(
                            imageVector = iconImage,
                            contentDescription = description
                        )
                    }

                },
                visualTransformation =


                if (cpasswordVisible.value) VisualTransformation.None
                else
                    PasswordVisualTransformation()
            )


            Spacer(modifier = Modifier.height(10.dp))

            Row {
                Button(
                    onClick = { onEvent(RegisterEvent.OnSaveUser) },
                    shape = MaterialTheme.shapes.extraLarge,

                    enabled = !state.isLoading
                ) {
                    if (state.isLoading) {
                        Text(text = "Registering ...")
                    } else {
                        Text("Register")
                    }
                }
            }

            Row(
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Text(
                    text = "Already have an account?",
                    fontWeight = FontWeight.Medium,
                )

                Text(
                    modifier = Modifier
                        .padding(start = 3.dp)
                        .clickable {
                            onBack()
                        },
                    text = "Login",
                    fontWeight = FontWeight.Bold,
                    color =  MaterialTheme.colorScheme.primary

                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun RegisterScreenPreview() {
    RegisterScreen()
}