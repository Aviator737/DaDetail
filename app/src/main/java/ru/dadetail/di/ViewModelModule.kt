package ru.dadetail.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.dadetail.ui.MainViewModel
import ru.dadetail.ui.screens.auth.login.LoginViewModel
import ru.dadetail.ui.screens.cart.CartViewModel
import ru.dadetail.ui.screens.catalog.CatalogViewModel
import ru.dadetail.ui.screens.orders.orders.OrdersViewModel
import ru.dadetail.ui.screens.orders.positions.PositionsViewModel
import ru.dadetail.ui.screens.search.SearchViewModel
import ru.dadetail.ui.screens.search_results.SearchResultsViewModel
import ru.dadetail.ui.screens.sto.STOViewModel
import ru.dadetail.ui.screens.welcome.WelcomeViewModel

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { WelcomeViewModel() }
    viewModel { SearchViewModel(get(), get()) }
    viewModel { STOViewModel(get()) }
    viewModel { CatalogViewModel() }
    viewModel { CartViewModel(get(), get()) }
    viewModel { OrdersViewModel(get()) }
    viewModel { PositionsViewModel(get()) }
    viewModel { SearchResultsViewModel(get(), get(), get()) }
}