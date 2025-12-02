package marcel.uni.gamifiedplanner.ui.auth

import android.util.Patterns.EMAIL_ADDRESS
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.auth.AuthResult
import marcel.uni.gamifiedplanner.domain.auth.usecase.login.LogInResult
import marcel.uni.gamifiedplanner.ui.components.NavButton
import marcel.uni.gamifiedplanner.ui.navigation.AppRoutes
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginView(
    nav: NavHostController,
    vm: AuthViewModel = koinViewModel()
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }

    var isEmailValid by remember { mutableStateOf(true) }

    var isError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Surface(
            modifier = Modifier
                .wrapContentSize(Alignment.Center)
                .padding(15.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "Login",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    value = email,
                    onValueChange = {
                        if (EMAIL_ADDRESS.matcher(email).matches().not()) {
                            isEmailValid = false
                        } else {
                            isEmailValid = true
                        }
                        email = it.trim()
                    },
                    label = { Text("Email") },
                    isError = !isEmailValid,
                    singleLine = true,
                    shape = RoundedCornerShape(20.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    singleLine = true,
                    visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = {
                            showPassword = !showPassword
                        }) {
                        }
                    }, shape = RoundedCornerShape(20.dp)

                )
                Spacer(modifier = Modifier.height(10.dp))

                if (isError) {
                    Text(
                        text = errorMessage,
                        color = MaterialTheme.colorScheme.error
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }


                Button(onClick = {
                    vm.login(email, password, { result ->
                        when (result) {
                            is LogInResult.Success -> {
                            }

                            is LogInResult.Failure -> {
                                isError = true
                                errorMessage = result.error.localizedMessage ?: "Unknown error"
                            }

                            is LogInResult.ValidationError -> {
                                isError = true
                                errorMessage = result.message
                            }
                        }
                    })
                }) {
                    Text("Login")
                }

                Spacer(modifier = Modifier.height(10.dp))

                NavButton("Don't have an account? Register", AppRoutes.Register, nav)
            }
        }


    }


}