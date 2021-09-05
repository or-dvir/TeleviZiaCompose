package com.hotmail.or_dvir.televiziacompose.ui.login_register

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
fun PasswordField(
    state: TextFieldState,
    modifier: Modifier = Modifier,
    onTextChanged: (String) -> Unit
)
{
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

    EmailPasswordTextField(
        state = state,
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
    state: TextFieldState,
    @StringRes hint: Int,
    keyboardOptions: KeyboardOptions,
    visualTransformation: VisualTransformation,
    modifier: Modifier,
    trailingIcon: @Composable (() -> Unit)?,
    onTextChanged: (String) -> Unit
)
{
    val showError = state.hasError()

    Column {
        OutlinedTextField(
            visualTransformation = visualTransformation,
            value = state.text,
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
                text = state.error,
                color = MaterialTheme.colors.error
            )
        }
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
        val passwordState = remember { TextFieldState() }
        val maxWidthModifier = Modifier.fillMaxWidth()

        //email field
        EmailPasswordTextField(
            state = emailState,
            hint = R.string.hint_email,
            keyboardOptions = KeyboardOptions.Default,
            visualTransformation = VisualTransformation.None,
            modifier = maxWidthModifier,
            trailingIcon = null,
            onTextChanged = {
                emailState.apply {
                    text = it
                    //todo fix me. just for show
                    error = if (text.length > 3)
                    {
                        "max 3 characters"
                    } else
                    {
                        ""
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(5.dp))

        PasswordField(
            passwordState,
            maxWidthModifier
        ) {
            passwordState.apply {
                text = it
                //todo fix me. just for show
                error = if (text.length < 3)
                {
                    "min 3 characters"
                } else
                {
                    ""
                }
            }
        }

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
//endregion
