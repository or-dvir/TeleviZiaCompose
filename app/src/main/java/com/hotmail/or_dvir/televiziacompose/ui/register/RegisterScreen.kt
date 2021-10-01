package com.hotmail.or_dvir.televiziacompose.ui.register

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.hotmail.or_dvir.televiziacompose.R
import com.hotmail.or_dvir.televiziacompose.ui.register.RegisterViewModel.RegisterUiState
import com.hotmail.or_dvir.televiziacompose.ui.shared.LoadingIndicatorFullScreen
import com.hotmail.or_dvir.televiziacompose.ui.shared.OutlinedTextFieldWithError
import com.hotmail.or_dvir.televiziacompose.ui.shared.PasswordTextField
import com.hotmail.or_dvir.televiziacompose.ui.theme.TeleviZiaComposeTheme

@Composable
fun RegisterScreen(viewModel: RegisterViewModel)
{
    //todo look into landscape mode
    TeleviZiaComposeTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .border(2.dp, Color.Green)
        ) {
            val uiState by viewModel.uiState.observeAsState(RegisterUiState())

            //todo add action bar with back button
            val maxWidthModifier = Modifier.fillMaxWidth()
            val spacerModifier = Modifier.height(5.dp)

            val focusManager = LocalFocusManager.current

            val clearFocus = { focusManager.clearFocus() }
            val clearFocusAndRegister = {
                clearFocus()
                viewModel.onRegisterClicked()
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(2.dp)
                    .border(2.dp, Color.Red)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                //email field
                OutlinedTextFieldWithError(
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(
                        onAny = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                    text = uiState.emailText,
                    error = uiState.emailError,
                    hint = R.string.hint_email,
                    modifier = maxWidthModifier,
                    onTextChanged = { viewModel.onEmailInputChanged(it) }
                )

                Spacer(modifier = spacerModifier)

                PasswordTextField(
                    imeAction = ImeAction.Next,
                    keyboardActions = KeyboardActions(
                        onAny = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                    text = uiState.passwordText,
                    error = uiState.passwordError,
                    modifier = maxWidthModifier,
                    onTextChanged = { viewModel.onPasswordInputChanged(it) }
                )

                Spacer(modifier = spacerModifier)

                //password confirmation
                PasswordTextField(
                    imeAction = ImeAction.Done,
                    keyboardActions = KeyboardActions(
                        onAny = { clearFocus() }
                    ),
                    text = uiState.passwordConfirmationText,
                    error = uiState.passwordConfirmationError,
                    hint = R.string.hint_passwordConfirmation,
                    modifier = maxWidthModifier,
                    onTextChanged = { viewModel.onPasswordConfirmationInputChanged(it) }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    modifier = maxWidthModifier,
                    onClick = { clearFocusAndRegister() }
                ) {
                    Text(stringResource(id = R.string.register))
                }
            }

            //this should be the LAST composable so it shows above everything else
            if (uiState.isLoading)
            {
                LoadingIndicatorFullScreen()
            }
        }
    }
}