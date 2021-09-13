package com.hotmail.or_dvir.televiziacompose.ui.login_register

import android.app.Application
import androidx.core.util.PatternsCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hotmail.or_dvir.televiziacompose.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel(private val app: Application) : AndroidViewModel(app)
{
    private val mainDispatcher = Dispatchers.Main

    private val _emailState = MutableLiveData(TextFieldState())
    val emailState: LiveData<TextFieldState> = _emailState
    private val _passwordState = MutableLiveData(TextFieldState())
    val passwordState: LiveData<TextFieldState> = _passwordState

    private val _loginState = MutableLiveData(LoginState.Idle)
    observe this in the login screen!!!
    val loginState: LiveData<LoginState> = _loginState

    fun onEmailInputChanged(newInput: String)
    {
        _emailState.value = _emailState.value!!.copy(text = newInput)
    }

    fun onPasswordInputChanged(newInput: String)
    {
        _passwordState.value = _passwordState.value!!.copy(text = newInput)
    }

    fun login()
    {
        if (validateEmail() && validatePassword())
        {
            //valid input. perform login
            viewModelScope.launch(mainDispatcher) {
                _loginState.value = LoginState.Loading
                //todo implement actual login functionality
                // add error state with message (user doesnt exist, wrong password)
                //      add user functionality to database (including simulated failure
                delay(3000)
                _loginState.value = LoginState.Success
            }
        }
    }

    private fun validatePassword(): Boolean
    {
        val isValid = _passwordState.value?.hasText() ?: false

        if (!isValid)
        {
            _passwordState.value = _passwordState.value!!.copy(
                error = app.getString(R.string.error_emptyField)
            )
        }

        return isValid
    }

    private fun validateEmail(): Boolean
    {
        val isValid = _emailState.value?.text?.let {
            PatternsCompat.EMAIL_ADDRESS.matcher(it).matches()
        } ?: false

        if (!isValid)
        {
            _emailState.value = _emailState.value!!.copy(
                error = app.getString(R.string.error_invalidEmail)
            )
        }

        return isValid
    }
}

enum class LoginState
{
    Idle,
    Loading,
    Success
}