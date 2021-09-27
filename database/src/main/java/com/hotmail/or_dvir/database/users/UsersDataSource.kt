package com.hotmail.or_dvir.database.users

import kotlin.random.Random

object UsersDataSource
{
    private val allUsers = listOf<UserEntity>()

    /**
     * @return UserError? an error, or null if successful
     */
    fun register(user: UserEntity): LoginResponse
    {
        //todo
        return LoginResponse.Success
    }

    /**
     * @return UserError? an error, or null if successful
     */
    fun login(email: String, password: String): LoginResponse
    {
        val dbUser = allUsers.find { it.email == email } ?: return LoginResponse.NonExistingUser

        if (dbUser.password != password)
        {
            return LoginResponse.WrongPassword
        }

        return if (Random.nextInt(100) <= 80)
        {
            LoginResponse.Success
        } else
        {
            LoginResponse.NetworkError
        }
    }

    ////////////////////////////
    ////////////////////////////
    ////////////////////////////
    ////////////////////////////

    sealed class LoginResponse
    {
        object Success : LoginResponse()
        object NonExistingUser : LoginResponse()
        object WrongPassword : LoginResponse()
        object NetworkError : LoginResponse()
    }
}