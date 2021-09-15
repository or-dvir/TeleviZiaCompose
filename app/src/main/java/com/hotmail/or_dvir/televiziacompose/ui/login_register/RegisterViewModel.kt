package com.hotmail.or_dvir.televiziacompose.ui.login_register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hotmail.or_dvir.televiziacompose.repositories.UsersRepository
import kotlinx.coroutines.Dispatchers
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RegisterViewModel(private val app: Application) : AndroidViewModel(app), KoinComponent
{
    private val mainDispatcher = Dispatchers.Main
    private val usersRepo: UsersRepository by inject()

    private val _uiState = MutableLiveData(RegisterUiState())
    val uiState: LiveData<RegisterUiState> = _uiState
}