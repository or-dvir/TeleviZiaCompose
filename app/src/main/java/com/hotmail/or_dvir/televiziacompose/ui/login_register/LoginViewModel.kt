package com.hotmail.or_dvir.televiziacompose.ui.login_register

import android.app.Application
import androidx.core.util.PatternsCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hotmail.or_dvir.database.users.UsersDataSource.UserError.NetworkError
import com.hotmail.or_dvir.database.users.UsersDataSource.UserError.NonExistingUser
import com.hotmail.or_dvir.database.users.UsersDataSource.UserError.WrongPassword
import com.hotmail.or_dvir.televiziacompose.R
import com.hotmail.or_dvir.televiziacompose.repositories.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginViewModel(private val app: Application) : AndroidViewModel(app), KoinComponent
{
    private val mainDispatcher = Dispatchers.Main
    private val usersRepo: UsersRepository by inject()

    private val _uiState = MutableLiveData(LoginUiState())
    val uiState: LiveData<LoginUiState> = _uiState

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
                passwordText = newInput,
                passwordError = "", //reset any errors
            )
        )

    }

    fun onLoginClicked()
    {
        //the validate*() functions update _uiState.
        //therefore, we need to call BOTH so the full state is updated.
        //if we put them directly in the "if" statement and email is invalid,
        //validatePassword() will be skipped!
        val validEmail = validateEmail()
        val validPassword = validatePassword()

        if (validEmail && validPassword)
        {
            //valid input. perform login
            viewModelScope.launch(mainDispatcher) {
                updateUiState(
                    uiState.value!!.copy(
                        isLoading = true,
                        loginError = "", //reset any errors
                        emailError = "", //reset any errors
                        passwordError = "", //reset any errors
                    )
                )

                when (usersRepo.login(uiState.value!!.emailText, uiState.value!!.passwordText))
                {
                    null ->
                    {/* //todo login successful*/
                    }
                    NetworkError -> onLoginResult(app.getString(R.string.error_networkError))
                    NonExistingUser -> onLoginResult(app.getString(R.string.error_nonExistingUser))
                    WrongPassword -> onLoginResult(app.getString(R.string.error_wrongPassword))
                }
            }
        }
    }

    private fun onLoginResult(error: String?)
    {
        updateUiState(
            uiState.value!!.copy(
                isLoading = false,
                loginError = error.orEmpty()
            )
        )
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

    private fun updateUiState(newState: LoginUiState)
    {
        _uiState.value = newState
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
        val isLoading: Boolean = false,
        val loginError: String = ""
    )
}