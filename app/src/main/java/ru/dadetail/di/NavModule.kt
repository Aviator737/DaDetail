package ru.dadetail.di

import org.koin.dsl.module
import ru.dadetail.ui.nav.AppNavigator

val navModule = module {
    single { AppNavigator() }
}