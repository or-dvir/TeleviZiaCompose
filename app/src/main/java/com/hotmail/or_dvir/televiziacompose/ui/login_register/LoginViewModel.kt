package com.hotmail.or_dvir.televiziacompose.ui.login_register

import android.app.Application
import androidx.core.util.PatternsCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers

class LoginViewModel(app: Application) : AndroidViewModel(app)
{
    private val mainDispatcher = Dispatchers.Main

    private val _emailState = MutableLiveData(TextFieldState())
    val emailState: LiveData<TextFieldState> = _emailState
    private val _passwordState = MutableLiveData(TextFieldState())
    val passwordState: LiveData<TextFieldState> = _passwordState

    fun onEmailInputChanged(newInput: String)
    {
        _emailState.value = _emailState.value!!.copy(text = newInput)
    }

    fun onPasswordInputChanged(newInput: String)
    {
        _passwordState.value = _passwordState.value!!.copy(text = newInput)
    }

    //todo
    // hold and update email state
    // hold and update password state

    /**
     * @return String error message, or `null` if success
     */
    //todo fix me
//    fun login(email: String, password: String): String?
//    {
//        add input validation(isEmailValid, no empty fields, what else?)
//        viewModelScope.launch(mainDispatcher) {
//            return if (isEmailValid(email))
//            {
//                //todo implement actual login functionality
//                delay(3000)
//                null
//            } else
//            {
//                app.getString(R.string.error_invalidEmail)
//            }
//        }
//    }

    private fun isEmailValid(email: String) = PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
}