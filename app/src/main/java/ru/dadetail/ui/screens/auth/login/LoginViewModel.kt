package ru.dadetail.ui.screens.auth.login

import androidx.lifecycle.viewModelScope
import ru.dadetail.data.repository.AuthRepository
import ru.dadetail.ui.base.BaseViewModel
import ru.dadetail.ui.nav.AppNavigator
import ru.dadetail.ui.nav.Destination
import ru.dadetail.util.extentions.launchSafe

class LoginViewModel(
    private val authRepository: AuthRepository,
    private val appNavigator: AppNavigator
): BaseViewModel<LoginContract.Event, LoginContract.State, LoginContract.Effect>() {
    override fun createInitialState() = LoginContract.State()

    override fun handleEvent(event: LoginContract.Event) {
        when (event) {
            is LoginContract.Event.OnEmailInput -> setState { copy(email = event.data) }
            is LoginContract.Event.OnLoginInput -> setState { copy(login = event.data) }
            is LoginContract.Event.OnPasswordInput -> setState { copy(password = event.data) }
            is LoginContract.Event.OnPhoneInput -> setState { copy(phone = event.data) }
            is LoginContract.Event.OnLoginOptionTabChanged -> setState { copy(loginOption = event.tab) }
            is LoginContract.Event.OnDoLogin -> doLogin()
        }
    }

    private fun doLogin() = viewModelScope.launchSafe(::onError) {
        setState { copy(isLoading = true) }
        val login = when(currentState.loginOption) {
            LoginOption.LOGIN -> ViewLogin(
                login = currentState.login.text,
                password = currentState.password.text
            )
            LoginOption.EMAIL -> ViewLogin(
                email = currentState.email.text,
                password = currentState.password.text
            )
            LoginOption.PHONE -> ViewLogin(
                phone = currentState.phone.text,
                password = currentState.password.text
            )
        }

        if (
            login.login?.isEmpty() == true
            || login.email?.isEmpty() == true
            || login.phone?.isEmpty() == true
            || login.password.isEmpty()
        ) return@launchSafe

        val result = authRepository.login(login)
        setState { copy(isLoading = false) }
        if (result.isSuccessful) {
            appNavigator.tryNavigateTo(
                Destination.SearchScreen()
            )
        }
    }
}