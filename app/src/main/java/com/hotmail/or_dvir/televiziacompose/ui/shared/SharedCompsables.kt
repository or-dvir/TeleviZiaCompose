package com.hotmail.or_dvir.televiziacompose.ui.shared

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.hotmail.or_dvir.televiziacompose.R

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

@Composable
fun OutlinedTextFieldWithError(
    text: String,
    error: String,
    @StringRes hint: Int,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
    onTextChanged: (String) -> Unit
) {
    val showError by remember(error) { mutableStateOf(error.isNotBlank()) }

    Column {
        OutlinedTextField(
            keyboardActions = keyboardActions,
            visualTransformation = visualTransformation,
            value = text,
            isError = showError,
            modifier = modifier,
            singleLine = singleLine,
            onValueChange = { onTextChanged(it) },
            label = { Text(text = stringResource(id = hint)) },
            keyboardOptions = keyboardOptions,
            trailingIcon = trailingIcon
        )

        if (showError) {
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
fun PasswordTextField(
    text: String,
    error: String,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.None,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    @StringRes hint: Int = R.string.hint_password,
    onTextChanged: (String) -> Unit
) {
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

    val passwordTransformation: VisualTransformation
    @DrawableRes val passwordIconId: Int
    @StringRes val passwordContentDescriptionId: Int

    if (isPasswordVisible) {
        passwordIconId = R.drawable.ic_visibility_off
        passwordContentDescriptionId = R.string.contentDescription_hidePassword
        passwordTransformation = VisualTransformation.None
    } else {
        //password is hidden
        passwordIconId = R.drawable.ic_visibility_on
        passwordContentDescriptionId = R.string.contentDescription_showPassword
        passwordTransformation = PasswordVisualTransformation()
    }

    OutlinedTextFieldWithError(
        text = text,
        error = error,
        hint = hint,
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction,
            keyboardType = KeyboardType.Password
        ),
        keyboardActions = keyboardActions,
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
