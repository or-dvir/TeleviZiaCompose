package com.hotmail.or_dvir.televiziacompose.ui.login_register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hotmail.or_dvir.televiziacompose.R
import com.hotmail.or_dvir.televiziacompose.ui.login_register.LoginViewModel.LoginUiState
import com.hotmail.or_dvir.televiziacompose.ui.shared.OutlinedTextFieldWithError
import com.hotmail.or_dvir.televiziacompose.ui.shared.PasswordTextField
import com.hotmail.or_dvir.televiziacompose.ui.theme.LinkColor
import com.hotmail.or_dvir.televiziacompose.ui.theme.TeleviZiaComposeTheme

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onRegisterClicked: (email: String, password: String) -> Unit
)
{
    //todo look into landscape mode
    TeleviZiaComposeTheme {
        //todo check padding... add modifier?
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            //todo isn't it bad if i put an initial value here AND in the view model?
            val uiState by viewModel.uiState.observeAsState(LoginUiState())

            //todo logo (and app name???)
            LoginRegister(viewModel, onRegisterClicked)

            //this should be the LAST composable so it shows above everything else
            if (uiState.isLoading)
            {
                LoadingIndicatorFullScreen()
            }

            //todo add ALL composables here
        }
    }
}

@Composable
fun LoadingIndicatorFullScreen(modifier: Modifier = Modifier)
{
    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                //do nothing. the user should not be able to change anything while loading
            },
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun LoginRegister(
    viewModel: LoginViewModel,
    onRegisterClicked: (email: String, password: String) -> Unit
)
{
    //todo should i pass the uiState only instead of the view model?
    // i am already observing the state in the top level "LoginScreen".
    // is it a problem if i am also observing here?

    //todo do i need to remember the focused field?
    // it is not automatically saved. decide if to save or not

    Column(
        modifier = Modifier.padding(16.dp),
    ) {

        //todo isn't it bad if i put an initial value here AND in the view model?
        val uiState by viewModel.uiState.observeAsState(LoginUiState())
        val maxWidthModifier = Modifier.fillMaxWidth()

        //email field
        OutlinedTextFieldWithError(
            text = uiState.emailText,
            error = uiState.emailError,
            hint = R.string.hint_email,
            modifier = maxWidthModifier,
            onTextChanged = { viewModel.onEmailInputChanged(it) }
        )

        Spacer(modifier = Modifier.height(5.dp))

        PasswordTextField(
            text = uiState.passwordText,
            error = uiState.passwordError,
            modifier = maxWidthModifier,
            onTextChanged = { viewModel.onPasswordInputChanged(it) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = maxWidthModifier,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val focusManager = LocalFocusManager.current
            val clearFocus = { focusManager.clearFocus() }

            Text(
                modifier = Modifier
                    .clickable {
                        clearFocus()
                        onRegisterClicked(uiState.emailText, uiState.passwordText)
                    }
                    .padding(5.dp),
                text = stringResource(id = R.string.register),
                color = LinkColor
            )

            Button(
                onClick = {
                    clearFocus()
                    viewModel.onLoginClicked()
                }
            ) {
                Text(stringResource(id = R.string.login))
            }
        }

        //todo register button
    }
}

//region
@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview()
{
    //todo how do i pass the view model here?
//    LoginScreen()
}
//endregion
