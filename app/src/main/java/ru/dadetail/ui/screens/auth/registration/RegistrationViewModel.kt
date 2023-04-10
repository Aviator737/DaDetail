package ru.dadetail.ui.screens.auth.registration

import ru.dadetail.data.repository.AuthRepository
import ru.dadetail.ui.base.BaseViewModel

class RegistrationViewModel(
    private val authRepository: AuthRepository
): BaseViewModel<RegistrationContract.Event, RegistrationContract.State, RegistrationContract.Effect>() {
    override fun createInitialState() = RegistrationContract.State()

    override fun handleEvent(event: RegistrationContract.Event) {
        when(event) {

            else -> {}
        }
    }

}