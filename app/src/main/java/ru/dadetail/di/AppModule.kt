package ru.dadetail.di

import org.koin.dsl.module

val appModule = module {
    includes(
        apiModule,
        navModule,
        repositoryModule,
        viewModelModule
    )
}