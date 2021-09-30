package com.hotmail.or_dvir.televiziacompose.ui.register

import android.app.Application
import androidx.core.util.PatternsCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hotmail.or_dvir.database.users.UsersDataSource
import com.hotmail.or_dvir.televiziacompose.R
import com.hotmail.or_dvir.televiziacompose.repositories.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RegisterViewModel(private val app: Application) : AndroidViewModel(app), KoinComponent
{
    //todo note:
    // a lot of code is duplicated from login here, but we are going to change the whole login
    // process with firebase so no need to waste time creating shared components...

    private val mainDispatcher = Dispatchers.Main
    private val usersRepo: UsersRepository by inject()

    private val _uiState = MutableLiveData(RegisterUiState())
    val uiState: LiveData<RegisterUiState> = _uiState

    //    private val registerEventsChannel = Channel<RegisterEvent>()
    private val registerEventsChannel = Channel<UsersDataSource.RegisterResponse>()
    val registerEventsFlow = registerEventsChannel.receiveAsFlow()

    fun onEmailInputChanged(newInput: String)
    {
        updateUiState(
            uiState.value!!.copy(
                emailError = "", //reset any errors
                emailText = newInput
            )
        )
    }

    fun onPasswordInputChanged(newInput: String)
    {
        updateUiState(
            uiState.value!!.copy(
                passwordError = "", //reset any errors
                passwordText = newInput,
            )
        )
    }

    fun onPasswordConfirmationInputChanged(newInput: String)
    {
        updateUiState(
            uiState.value!!.copy(
                passwordConfirmationError = "", //reset any errors
                passwordConfirmationText = newInput,
            )
        )
    }

    private fun clearAllErrors()
    {
        updateUiState(
            uiState.value!!.copy(
                emailError = "",
                passwordError = "",
                passwordConfirmationError = "",
            )
        )
    }

    fun onRegisterClicked()
    {
        clearAllErrors()

        //the validate*() functions update _uiState.
        //therefore, we need to call ALL so the full state is updated.
        //if we put them directly in the "if" statement and email is invalid for example,
        //validatePassword() will be skipped!
        val validEmail = validateEmail()
        val validPassword = validatePassword()
        val validPasswordConfirmation = validatePasswordConfirmation()

        if (validEmail && validPassword && validPasswordConfirmation)
        {
            //valid input. perform login
            viewModelScope.launch(mainDispatcher) {
                updateUiState(
                    uiState.value!!.copy(isLoading = true)
                )

                val registerResult =
                    usersRepo.register(uiState.value!!.emailText, uiState.value!!.passwordText)

                updateUiState(
                    uiState.value!!.copy(isLoading = false)
                )

                registerEventsChannel.send(registerResult)
            }
        }
    }

    private fun validateEmail(): Boolean
    {
        _uiState.value!!.apply {
            val isEmailValid = PatternsCompat.EMAIL_ADDRESS.matcher(emailText.trim()).matches()
            if (!isEmailValid)
            {
                updateUiState(
                    _uiState.value!!.copy(
                        emailError = app.getString(R.string.error_invalidEmail)
                    )
                )

                return false
            }

            return true
        }
    }

    private fun validatePassword(): Boolean
    {
        _uiState.value!!.apply {
            val isPasswordEmpty = passwordText.isBlank()
            if (isPasswordEmpty)
            {
                updateUiState(
                    _uiState.value!!.copy(
                        passwordError = app.getString(R.string.error_emptyField)
                    )
                )

                return false
            }

            return true
        }
    }

    private fun validatePasswordConfirmation(): Boolean
    {
        _uiState.value!!.apply {
            val doPasswordsMatch = passwordText == passwordConfirmationText
            if (!doPasswordsMatch)
            {
                updateUiState(
                    _uiState.value!!.copy(
                        passwordConfirmationError = app.getString(R.string.error_passwordDoesNotMatch)
                    )
                )

                return false
            }

            return true
        }
    }

    private fun updateUiState(newState: RegisterUiState)
    {
        _uiState.value = newState
    }

    ////////////////////////////////
    ////////////////////////////////
    ////////////////////////////////
    ////////////////////////////////

    data class RegisterUiState(
        val emailText: String = "",
        val emailError: String = "",
        val passwordText: String = "",
        val passwordError: String = "",
        val passwordConfirmationText: String = "",
        val passwordConfirmationError: String = "",
        val isLoading: Boolean = false
    )
}