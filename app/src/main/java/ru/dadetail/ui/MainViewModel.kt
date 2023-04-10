package ru.dadetail.ui

import androidx.lifecycle.ViewModel
import ru.dadetail.ui.nav.AppNavigator

class MainViewModel(
    private val appNavigator: AppNavigator
) : ViewModel() {
    val navigationFlow = appNavigator.navigationFlow
}