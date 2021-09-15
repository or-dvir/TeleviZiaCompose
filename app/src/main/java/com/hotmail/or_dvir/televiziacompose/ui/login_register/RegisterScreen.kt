package com.hotmail.or_dvir.televiziacompose.ui.login_register

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hotmail.or_dvir.televiziacompose.ui.theme.TeleviZiaComposeTheme

@Composable
fun RegisterScreen(viewModel: RegisterViewModel)
{
    //todo look into landscape mode
    TeleviZiaComposeTheme {
        //todo check padding... add modifier?
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            Text("register fragment")

            //todo copied from login screen. fix me!

//            //todo isn't it bad if i put an initial value here AND in the view model?
//            val uiState by viewModel.uiState.observeAsState(RegisterUiState())
//
//            LoginRegister(viewModel)
//
//            uiState.loginError.let {
//                if (it.isNotBlank())
//                {
//                    val onDialogDismissed = {
//                        viewModel.resetLoginError()
//                    }
//
//                    AlertDialog(
//                        onDismissRequest = onDialogDismissed,
//                        confirmButton = {
//                            TextButton(onClick = onDialogDismissed) {
//                                Text(stringResource(id = R.string.ok))
//                            }
//                        },
//                        text = {
//                            Text(it)
//                        }
//                    )
//                }
//            }
//
//            //this should be the LAST composable so it shows above everything else
//            if (uiState.isLoading)
//            {
//                LoadingIndicatorFullScreen()
//            }
//
//            //todo add ALL composables here
        }
    }
}