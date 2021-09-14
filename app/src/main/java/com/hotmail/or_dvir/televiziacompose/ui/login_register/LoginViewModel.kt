package com.hotmail.or_dvir.televiziacompose.ui.login_register

import android.app.Application
import androidx.core.util.PatternsCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hotmail.or_dvir.televiziacompose.R
import kotlinx.coroutines.Dispatchers

class LoginViewModel(private val app: Application) : AndroidViewModel(app)
{
    private val mainDispatcher = Dispatchers.Main

    private val _uiState = MutableLiveData(LoginUiState())
    val uiState: LiveData<LoginUiState> = _uiState

    fun onEmailInputChanged(newInput: String)
    {
        _uiState.value = uiState.value!!.copy(emailText = newInput)
    }

    fun onPasswordInputChanged(newInput: String)
    {
        _uiState.value = uiState.value!!.copy(passwordText = newInput)
    }

//    fun login()
//    {
//        if (validateEmail() && validatePassword())
//        {
//            //valid input. perform login
//            viewModelScope.launch(mainDispatcher) {
//                _loginState.value = LoginState.Loading
//                //todo implement actual login functionality
//                // add error state with message (user doesnt exist, wrong password)
//                //      add user functionality to database (including simulated failure
//                delay(3000)
//                _loginState.value = LoginState.Success
//            }
//        }
//    }

    private fun validatePassword(): Boolean
    {
        _uiState.value!!.apply {
            val isPasswordEmpty = passwordText.isBlank()
            if (isPasswordEmpty)
            {
                _uiState.value = _uiState.value!!.copy(
                    passwordError = app.getString(R.string.error_emptyField)
                )

                return false
            }

            return true
        }
    }

    private fun validateEmail(): Boolean
    {
        _uiState.value!!.apply {
            val isEmailValid = PatternsCompat.EMAIL_ADDRESS.matcher(emailText).matches()
            if (!isEmailValid)
            {
                _uiState.value = _uiState.value!!.copy(
                    emailError = app.getString(R.string.error_invalidEmail)
                )

                return false
            }

            return true
        }
    }

    ////////////////////////////////
    ////////////////////////////////
    ////////////////////////////////
    ////////////////////////////////

    data class LoginUiState(
        val emailText: String = "",
        val emailError: String = "",
        val passwordText: String = "",
        val passwordError: String = "",
        val isLoading: Boolean = false
    )
}