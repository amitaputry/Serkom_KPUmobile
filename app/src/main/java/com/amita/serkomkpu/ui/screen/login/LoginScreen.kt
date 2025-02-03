package com.amita.serkomkpu.ui.screen.login

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.amita.serkomkpu.ui.component.KpuButton
import com.amita.serkomkpu.ui.component.KpuTextField
import com.amita.serkomkpu.ui.component.KpuTextFieldType
import com.amita.serkomkpu.ui.screen.destinations.HomeScreenDestination
import com.amita.serkomkpu.ui.screen.destinations.LoginScreenDestination
import com.amita.serkomkpu.ui.theme.SerkomKPUTheme
import com.amita.serkomkpu.ui.theme.poppinsFontFamily
import com.amita.serkomkpu.ui.theme.seed
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalPermissionsApi
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Destination
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator = EmptyDestinationsNavigator,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()

    val loginScreenUiState by loginViewModel.loginScreenUiState.collectAsState()
    val btnLoginEnabled by loginViewModel.btnLoginEnabled.collectAsState(initial = false)

    val navigateToMainScreen: () -> Unit = {
        navigator.navigate(HomeScreenDestination()) {
            popUpTo(LoginScreenDestination.route) {
                inclusive = true
            }
        }
    }

    Scaffold { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            LoginHeader()
            Spacer(modifier = Modifier.height(45.dp))
            KpuTextField(
                label = "Masukan Email",
                text = loginScreenUiState.email,
                onTextChanged = loginViewModel::updateEmail,
                placeholder = "Masukan Email anda"
            )
            Spacer(modifier = Modifier.height(20.dp))
            KpuTextField(
                label = "Password",
                text = loginScreenUiState.password,
                onTextChanged = loginViewModel::updatePassword,
                placeholder = "Masukan password anda",
                type = KpuTextFieldType.Password
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(
                    text = "Lupa password?",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = seed,
                    )
                )
            }
            KpuButton(
                text = "Masuk",
                onButtonClicked = navigateToMainScreen,
                modifier = Modifier.fillMaxWidth(),
                enabled = btnLoginEnabled
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Belum punya akun?",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF000000),
                    )
                )
                TextButton(
                    onClick = { /*TODO*/ },
                    contentPadding = PaddingValues(all = 2.dp)
                ) {
                    Text(
                        text = "Daftar",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = seed,
                        )
                    )
                }
            }
        }
    }
}

@FlowPreview
@ExperimentalPermissionsApi
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewLoginScreen() {
    SerkomKPUTheme {
        Surface {
            LoginScreen()
        }
    }
}
