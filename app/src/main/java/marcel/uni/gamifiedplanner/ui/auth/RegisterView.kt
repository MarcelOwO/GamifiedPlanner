package marcel.uni.gamifiedplanner.ui.auth

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
import marcel.uni.gamifiedplanner.domain.auth.usecase.register.RegisterResult
import marcel.uni.gamifiedplanner.ui.components.NavButton
import marcel.uni.gamifiedplanner.ui.navigation.AppRoutes
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterView(
    nav: NavHostController,
    vm: AuthViewModel = koinViewModel()
) {
    var email by remember { mutableStateOf("") }
    var username by remember {mutableStateOf("")}
    var password by remember { mutableStateOf("") }

    var showPassword by remember { mutableStateOf(false) }
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
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = "Register",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    shape = RoundedCornerShape(20.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Username") },
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
                    vm.register(email,"Default User", password, { result ->
                        when (result){
                            is RegisterResult.Success -> {

                            }
                            is RegisterResult.ValidationError -> {
                                isError = true
                                errorMessage = result.message
                            }
                            is RegisterResult.Failure -> {
                                isError = true
                                errorMessage = result.error.localizedMessage ?: "Unknown error"
                            }
                        }


                    })
                }) {
                    Text("Login")
                }

                Spacer(modifier = Modifier.height(10.dp))




                NavButton("Already have an account? Login", AppRoutes.Login, nav)
            }
        }


    }


}

@Composable
fun Column() {
    TODO("Not yet implemented")
}