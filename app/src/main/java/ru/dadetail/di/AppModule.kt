package ru.dadetail.di

import org.koin.dsl.module
import ru.dadetail.ui.nav.AppNavigator

val appModule = module {
    includes(
        apiModule,
        navModule,
        repositoryModule,
        viewModelModule
    )
}