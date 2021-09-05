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
    modifier: Modifier = Modifier,
    text: String = "",
    error: String = "",
    onTextChanged: (String) -> Unit
)
{
    //todo make a general class for all TextFields
    // should hold text, error status/message
    val showError = error.isNotBlank()

    Column {
        //todo
        // add ime action "next" to move to password field
        // handle error
        //      invalid email
        OutlinedTextField(
            value = text,
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
                text = error,
                color = MaterialTheme.colors.error
            )
        }
    }
}

@Composable
fun PasswordField(
    modifier: Modifier = Modifier,
    text: String = "",
    onTextChanged: (String) -> Unit
)
{
    //todo add error in case the field is empty
    // (in caller - should create state object? shared with email?)

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
    // hide characters by default
    // add icon to show/hide characters
    // add ime action next to go to the "login" button
    OutlinedTextField(
        visualTransformation = passwordTransformation,
        value = text,
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
}

@Composable
fun UserInput()
{
    //todo do i need to remember the focused field?

    Column(
        modifier = Modifier.padding(16.dp),
    ) {
        var email by remember { mutableStateOf("") }
        var emailError by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        val maxWidthModifier = Modifier.fillMaxWidth()

        EmailField(
            modifier = maxWidthModifier,
            text = email,
            error = emailError
        ) {
            email = it
            emailError = if (email.length > 3)
            {
                "max 3 characters"
            } else
            {
                ""
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
    EmailField(error = "some error") {}
}
//endregion
