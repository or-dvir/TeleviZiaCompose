package com.hotmail.or_dvir.televiziacompose.ui.login_register

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hotmail.or_dvir.televiziacompose.R
import com.hotmail.or_dvir.televiziacompose.ui.login_register.LoginViewModel.LoginUiState
import com.hotmail.or_dvir.televiziacompose.ui.theme.TeleviZiaComposeTheme

@Composable
fun LoginScreen(viewModel: LoginViewModel)
{
    //todo look into landscape mode
    TeleviZiaComposeTheme {
        //todo logo (and app name???)
        LoginRegister(viewModel)
    }

    //todo add ALL composables here
}

@Composable
fun PasswordField(
    state: LoginUiState,
    modifier: Modifier = Modifier,
    onTextChanged: (String) -> Unit
)
{
    //todo this will not keep for configuration changes
    var isPasswordVisible by remember { mutableStateOf(false) }

    val passwordTransformation: VisualTransformation
    @DrawableRes val passwordIconId: Int
    @StringRes val passwordContentDescriptionId: Int

    if (isPasswordVisible)
    {
        passwordIconId = R.drawable.ic_visibility_off
        passwordContentDescriptionId = R.string.contentDescription_hidePassword
        passwordTransformation = VisualTransformation.None
    } else
    {
        //password is hidden
        passwordIconId = R.drawable.ic_visibility_on
        passwordContentDescriptionId = R.string.contentDescription_showPassword
        passwordTransformation = PasswordVisualTransformation()
    }

    EmailPasswordTextField(
        text = state.passwordText,
        error = state.passwordError,
        hint = R.string.hint_password,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = passwordTransformation,
        modifier = modifier,
        onTextChanged = onTextChanged,
        trailingIcon = {
            IconButton(
                onClick = { isPasswordVisible = !isPasswordVisible }
            ) {
                Icon(
                    painter = painterResource(passwordIconId),
                    contentDescription = stringResource(passwordContentDescriptionId)
                )
            }
        }
    )
}

@Composable
fun EmailPasswordTextField(
    text: String,
    error: String,
    @StringRes hint: Int,
    keyboardOptions: KeyboardOptions,
    visualTransformation: VisualTransformation,
    modifier: Modifier,
    trailingIcon: @Composable (() -> Unit)?,
    onTextChanged: (String) -> Unit
)
{
    val showError = error.isNotBlank()

    Column {
        OutlinedTextField(
            visualTransformation = visualTransformation,
            value = text,
            isError = showError,
            modifier = modifier,
            singleLine = true,
            onValueChange = { onTextChanged(it) },
            label = { Text(text = stringResource(id = hint)) },
            keyboardOptions = keyboardOptions,
            trailingIcon = trailingIcon
        )

        if (showError)
        {
            Text(
                modifier = Modifier.offset(16.dp, 0.dp),
                style = MaterialTheme.typography.caption,
                text = error,
                color = MaterialTheme.colors.error
            )
        }
    }
}

@Composable
fun LoginRegister(viewModel: LoginViewModel)
{
    //todo do i need to remember the focused field?

    Column(
        modifier = Modifier.padding(16.dp),
    ) {

        //todo isn't it bad if i put an initial value here AND in the view model?
        val uiState by viewModel.uiState.observeAsState(LoginUiState())
        val maxWidthModifier = Modifier.fillMaxWidth()

        //email field
        EmailPasswordTextField(
            text = uiState.emailText,
            error = uiState.emailError,
            hint = R.string.hint_email,
            keyboardOptions = KeyboardOptions.Default,
            visualTransformation = VisualTransformation.None,
            modifier = maxWidthModifier,
            trailingIcon = null,
            onTextChanged = { viewModel.onEmailInputChanged(it) }
        )

        Spacer(modifier = Modifier.height(5.dp))

        PasswordField(
            state = uiState,
            modifier = maxWidthModifier
        ) { viewModel.onPasswordInputChanged(it) }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = maxWidthModifier,
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = { viewModel.onLoginClicked() }
            ) {
                Text(stringResource(id = R.string.login))
            }
        }

        i stopped here
        loading state should probably be done by wrapping everything in a box composable
        where the box captures all user clicks.
                overall errors should also be handled there using a dialog or snackbar
        //todo login button
        //      reflect result of login success/failure in ui

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
