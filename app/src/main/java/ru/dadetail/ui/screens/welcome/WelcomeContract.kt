package ru.dadetail.ui.screens.welcome

import ru.dadetail.ui.base.UiEffect
import ru.dadetail.ui.base.UiEvent
import ru.dadetail.ui.base.UiState

class WelcomeContract {
    sealed class Event : UiEvent {

    }

    class State() : UiState

    sealed class Effect: UiEffect {
        sealed class Navigation: Effect() {

        }
    }
}