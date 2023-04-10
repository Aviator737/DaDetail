package ru.dadetail.ui.screens.sto

import ru.dadetail.data.repository.SearchRepository
import ru.dadetail.ui.base.BaseViewModel

class STOViewModel(
    private val searchRepository: SearchRepository
): BaseViewModel<STOContract.Event, STOContract.State, STOContract.Effect>() {
    override fun createInitialState() = STOContract.State()

    override fun handleEvent(event: STOContract.Event) {
        when(event) {
            else -> {}
        }
    }
}