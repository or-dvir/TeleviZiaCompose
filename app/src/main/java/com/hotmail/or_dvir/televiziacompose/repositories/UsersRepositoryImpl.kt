package com.hotmail.or_dvir.televiziacompose.repositories

import com.hotmail.or_dvir.database.users.UsersDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class UsersRepositoryImpl : UsersRepository
{
    private val ioDispatcher = Dispatchers.IO

    private suspend fun pretendToLoad()
    {
        delay(3000)
    }

    override suspend fun login(email: String, password: String): UsersDataSource.LoginResponse
    {
        return withContext(ioDispatcher) {
            pretendToLoad()
            UsersDataSource.login(email.trim(), password)
        }
    }

    override suspend fun register(email: String, password: String): UsersDataSource.LoginResponse
    {
        return withContext(ioDispatcher) {
            pretendToLoad()
            UsersDataSource.register(email, password)
        }
    }
}