package com.hotmail.or_dvir.televiziacompose.ui.login_register

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.hotmail.or_dvir.televiziacompose.ui.theme.TeleviZiaComposeTheme

@Composable
fun LoginScreen()
{
    //todo look into landscape mode
    TeleviZiaComposeTheme {
        //todo logo (and app name???)
        UserInput()
    }

    //todo add ALL composables here
}

@Composable
fun EmailField(
    state: TextFieldState,
    modifier: Modifier = Modifier,
    onTextChanged: (String) -> Unit
)
{
    val showError = state.hasError()

    Column {
        //todo
        // add ime action "next" to move to password field
        // handle error
        //      invalid email
        OutlinedTextField(
            value = state.text,
            modifier = modifier,
            isError = showError,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = { onTextChanged(it) },
            label = { Text(text = stringResource(id = R.string.hint_email)) }
        )

        if (showError)
        {
            Text(
                modifier = Modifier.offset(16.dp, 0.dp),
                style = MaterialTheme.typography.caption,
                text = state.error,
                color = MaterialTheme.colors.error
            )
        }
    }
}

@Composable
fun PasswordField(
    state: TextFieldState,
    modifier: Modifier = Modifier,
    onTextChanged: (String) -> Unit
)
{
    

    val showError = state.hasError()
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
        passwordIconId = R.drawable.ic_visibility_on
        passwordContentDescriptionId = R.string.contentDescription_showPassword
        passwordTransformation = PasswordVisualTransformation()
    }

    //todo
    // add ime action next to go to the "login" button
    OutlinedTextField(
        visualTransformation = passwordTransformation,
        value = state.text,
        isError = showError,
        modifier = modifier,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        onValueChange = { onTextChanged(it) },
        label = { Text(text = stringResource(id = R.string.hint_password)) },
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

    if (showError)
    {
        Text(
            modifier = Modifier.offset(16.dp, 0.dp),
            style = MaterialTheme.typography.caption,
            text = state.error,
            color = MaterialTheme.colors.error
        )
    }
}

@Composable
fun UserInput()
{
    //todo do i need to remember the focused field?

    Column(
        modifier = Modifier.padding(16.dp),
    ) {

        val emailState = remember { TextFieldState() }
        var password by remember { mutableStateOf("") }

        val maxWidthModifier = Modifier.fillMaxWidth()

        EmailField(
            emailState,
            modifier = maxWidthModifier,
        ) {
            emailState.apply {
                text = it
                error = if (text.length > 3)
                {
                    "max 3 characters"
                } else
                {
                    ""
                }
            }
        }
        PasswordField(
            modifier = maxWidthModifier,
            text = password
        ) {
            password = it
        }

        //todo password
        //todo login button
        //todo register button
    }
}

//region
@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview()
{
    LoginScreen()
}

@Preview(showBackground = true)
@Composable
fun EmailErrorPreview()
{
    EmailField(
        state = TextFieldState(error = "some error")
    ) { /*ignore*/ }
}
//endregion
