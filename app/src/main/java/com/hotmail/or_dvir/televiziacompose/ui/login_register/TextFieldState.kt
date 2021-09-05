package com.hotmail.or_dvir.televiziacompose.ui.login_register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

open class TextFieldState(
    text: String = "",
    error: String = "",
)
{
    var text by mutableStateOf(text)
    var error by mutableStateOf(error)

    fun hasText() = text.isBlank()
    fun hasError() = error.isBlank()
}