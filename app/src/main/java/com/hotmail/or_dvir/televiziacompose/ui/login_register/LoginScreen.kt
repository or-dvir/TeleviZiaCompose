package com.hotmail.or_dvir.televiziacompose.ui.login_register


import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.imePadding
import com.google.accompanist.insets.navigationBarsWithImePadding
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
) {
    TeleviZiaComposeTheme {
        ProvideWindowInsets {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                val uiState by viewModel.uiState.observeAsState(LoginUiState())

//                make text fields go above keyboard
                        Column(
                            modifier = Modifier.fillMaxSize().imePadding(),
//                            modifier = Modifier.fillMaxSize().navigationBarsWithImePadding(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Branding(
                                Modifier.padding(top = 75.dp)
                            )

                            Spacer(modifier = Modifier.height(30.dp))

                            LoginRegister(
                                uiState = uiState,
                                viewModel = viewModel,
                                onRegisterClicked = onRegisterClicked
                            )
                        }

                //this should be the LAST composable so it shows above everything else
                if (uiState.isLoading)
                {
                    LoadingIndicatorFullScreen()
                }
            }
        }
    }
}

@Composable
fun Branding(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(125.dp),
            painter = painterResource(id = R.drawable.ic_tv),
            colorFilter = ColorFilter.tint(Color.Red),
            contentDescription = stringResource(id = R.string.contentDescription_logo)
        )

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4,
            text = stringResource(id = R.string.app_name)
        )
    }
}

@Composable
fun LoginRegister(
    uiState: LoginUiState,
    viewModel: LoginViewModel,
    modifier: Modifier = Modifier,
    onRegisterClicked: (email: String, password: String) -> Unit
) {
    //todo focus is not "remembered" at the moment. how do i remember the focused field?

    Column(
        modifier = modifier.padding(16.dp),
    ) {

        val maxWidthModifier = Modifier.fillMaxWidth()
        val focusManager = LocalFocusManager.current

        val clearFocus = { focusManager.clearFocus() }
        val clearFocusAndLogin = {
            clearFocus()
            viewModel.onLoginClicked()
        }

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

        Spacer(modifier = Modifier.height(5.dp))

        PasswordTextField(
            imeAction = ImeAction.Go,
            keyboardActions = KeyboardActions(
                onAny = { clearFocusAndLogin() }
            ),
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
            //register button
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
            //login button
            Button(
                onClick = { clearFocusAndLogin() }
            ) {
                Text(stringResource(id = R.string.login))
            }
        }
    }
}

@Composable
fun LoadingIndicatorFullScreen(modifier: Modifier = Modifier) {
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

//region
@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    //todo how do i pass the view model here?
//    LoginScreen()
}
//endregion
