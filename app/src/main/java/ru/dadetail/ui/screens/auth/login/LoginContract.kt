package ru.dadetail.ui.screens.auth.login

import androidx.compose.ui.text.input.TextFieldValue
import ru.dadetail.BuildConfig
import ru.dadetail.ui.base.UiEffect
import ru.dadetail.ui.base.UiEvent
import ru.dadetail.ui.base.UiState

class LoginContract {
    sealed class Event : UiEvent {
        class OnLoginInput(val data: TextFieldValue): Event()
        class OnEmailInput(val data: TextFieldValue): Event()
        class OnPhoneInput(val data: TextFieldValue): Event()
        class OnPasswordInput(val data: TextFieldValue): Event()
        class OnLoginOptionTabChanged(val tab: LoginOption): Event()
        object OnDoLogin : Event()
    }

    data class State(
        val isLoading: Boolean = false,
        val login: TextFieldValue = TextFieldValue(
            if (BuildConfig.DEBUG) "204@autoplus76.ru" else ""
        ),
        val email: TextFieldValue = TextFieldValue(),
        val phone: TextFieldValue = TextFieldValue(),
        val password: TextFieldValue = TextFieldValue(
            if (BuildConfig.DEBUG) "123456" else ""
        ),
        val loginOption: LoginOption = LoginOption.LOGIN
    ) : UiState

    sealed class Effect: UiEffect
}