package ru.dadetail.ui.screens.catalog

import ru.dadetail.ui.base.BaseViewModel

class CatalogViewModel: BaseViewModel<CatalogContract.Event, CatalogContract.State, CatalogContract.Effect>() {
    override fun createInitialState() = CatalogContract.State()

    override fun handleEvent(event: CatalogContract.Event) {
        when(event) {

            else -> {}
        }
    }
}