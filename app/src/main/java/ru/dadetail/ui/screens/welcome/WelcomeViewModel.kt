package ru.dadetail.ui.screens.welcome

import ru.dadetail.ui.base.BaseViewModel

class WelcomeViewModel: BaseViewModel<WelcomeContract.Event, WelcomeContract.State, WelcomeContract.Effect>() {
    override fun createInitialState() = WelcomeContract.State()
    override fun handleEvent(event: WelcomeContract.Event) {
        when(event) {
            else -> {}
        }
    }
}