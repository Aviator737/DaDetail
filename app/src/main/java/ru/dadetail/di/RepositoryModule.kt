package ru.dadetail.di

import org.koin.dsl.module
import ru.dadetail.data.repository.*

val repositoryModule = module {
    factory { AuthRepository(get()) }
    factory { SearchRepository(get()) }
    factory { CartRepository(get()) }
    factory { PositionRepository(get()) }
    factory { OrderRepository(get()) }
}
