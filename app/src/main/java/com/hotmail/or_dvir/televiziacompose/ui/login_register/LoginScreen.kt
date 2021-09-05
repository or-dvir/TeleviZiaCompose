package com.hotmail.or_dvir.televiziacompose.ui.login_register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
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
            modifier = Modifier.fillMaxWidth(),
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
fun UserInput()
{
    //todo do i need to remember the focused field?

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        var email by remember { mutableStateOf("") }
        var error by remember { mutableStateOf("") }

        EmailField(
            text = email,
            error = error
        ) {
            email = it
            error = if (email.length > 3)
            {
                "max 3 characters"
            } else
            {
                ""
            }
        }

        //todo password
        // hide characters by default
        // add ime action next to go to the button
        // add icon to show/hide characters

        //todo login button
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
