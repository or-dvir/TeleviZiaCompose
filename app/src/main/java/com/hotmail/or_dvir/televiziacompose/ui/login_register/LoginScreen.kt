package com.hotmail.or_dvir.televiziacompose.ui.login_register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hotmail.or_dvir.televiziacompose.R
import com.hotmail.or_dvir.televiziacompose.ui.theme.TeleviZiaComposeTheme

@Composable
fun LoginScreen()
{
    TeleviZiaComposeTheme {
        UserNamePasswordSubmit()
    }
    //todo add ALL composables here
}

@Preview(
//    showSystemUi = true,
    showBackground = true
)
@Composable
fun LoginScreenPreview()
{
    LoginScreen()
}

@Preview(showBackground = true)
@Composable
fun EmailFieldPreview()
{
    EmailField("my@email.com")
}

@Preview(
    showBackground = true
)
@Composable
fun EmailFieldPreview_longText()
{
    EmailField("a very long text a very long text a very long text a very long text a very long text a very long text a very long text ")
}

@Composable
fun EmailField(text: String = "")
{
    var emailState by remember { mutableStateOf(text) }
    //todo
    // add ime action "next" to move to password field
    // invalid email handling
    // look into landscape mode
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        value = emailState,
        onValueChange = { emailState = it },
        label = {
            Text(text = stringResource(id = R.string.hint_email))
        }
    )
}

@Composable
fun UserNamePasswordSubmit()
{
    //todo should be stateful or stateless?
    // are other components interested? YES! WE NEED THE INPUT! -> stateless

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        EmailField()

        //todo password
        // hide characters by default
        // add ime action next to go to the button
        // add icon to show/hide characters

        //todo login button
    }
}

//todo taking up WAY too much computing power (refreshes on every change?)
// enable later!!!
//@Preview
//@Composable
//fun LoginPreview()
//{
//    TeleviZiaComposeTheme {
//        LoginScreen()
//    }
//}
