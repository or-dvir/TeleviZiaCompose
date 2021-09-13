package com.hotmail.or_dvir.televiziacompose.ui.login_register

data class TextFieldState(
    val text: String = "",
    val error: String = "",
)
{
    fun hasText() = text.isNotBlank()
    fun hasError() = error.isNotBlank()
}