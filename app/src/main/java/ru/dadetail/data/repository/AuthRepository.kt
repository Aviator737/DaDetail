package ru.dadetail.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.dadetail.data.api.AuthApi
import ru.dadetail.data.entity.Login
import ru.dadetail.ui.screens.auth.login.ViewLogin

class AuthRepository(private val customerApi: AuthApi) {
    suspend fun login(login: ViewLogin) = withContext(Dispatchers.IO) {
        customerApi.login(
            Login(login.login, login.email, login.phone, login.password)
        )
    }
}