package com.hotmail.or_dvir.televiziacompose.repositories

import com.hotmail.or_dvir.database.users.UsersDataSource

interface UsersRepository
{
    suspend fun login(email: String, password: String): UsersDataSource.LoginResponse
    suspend fun register(email: String, password: String): UsersDataSource.LoginResponse
}