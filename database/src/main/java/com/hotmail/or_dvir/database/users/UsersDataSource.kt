package com.hotmail.or_dvir.database.users

import kotlin.random.Random

object UsersDataSource
{
    private val allUsers = listOf<UserEntity>()

    /**
     * @return UserError? an error, or null if successful
     */
    fun register(user: UserEntity): UserError?
    {
        //todo
        return null
    }

    /**
     * @return UserError? an error, or null if successful
     */
    fun login(email: String, password: String): UserError?
    {
        val dbUser = allUsers.find { it.email == email } ?: return UserError.NonExistingUser

        if (dbUser.password != password)
        {
            return UserError.WrongPassword
        }

        return if (Random.nextInt(100) <= 80)
        {
            null
        } else
        {
            UserError.NetworkError
        }
    }

    ////////////////////////////
    ////////////////////////////
    ////////////////////////////
    ////////////////////////////

    sealed class UserError
    {
        object NonExistingUser : UserError()
        object WrongPassword : UserError()
        object NetworkError : UserError()
    }
}