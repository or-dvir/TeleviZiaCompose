package com.hotmail.or_dvir.televiziacompose.ui.login_register

import android.app.Application
import androidx.core.util.PatternsCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.hotmail.or_dvir.televiziacompose.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel(val app: Application) : AndroidViewModel(app)
{
    private val mainDispatcher = Dispatchers.Main

    /**
     * @return String error message, or `null` if success
     */
    //todo fix me
//    fun login(email: String, password: String): String?
//    {
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