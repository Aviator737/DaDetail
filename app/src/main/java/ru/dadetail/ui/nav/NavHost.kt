package ru.dadetail.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.dadetail.ui.screens.auth.login.LoginScreen
import ru.dadetail.ui.screens.cart.CartScreen
import ru.dadetail.ui.screens.catalog.CatalogScreen
import ru.dadetail.ui.screens.orders.OrdersContainerScreen
import ru.dadetail.ui.screens.orders.orders.OrdersScreen
import ru.dadetail.ui.screens.orders.positions.PositionsScreen
import ru.dadetail.ui.screens.search.SearchScreen
import ru.dadetail.ui.screens.search_results.SearchResultsScreen
import ru.dadetail.ui.screens.sto.STOScreen

@Composable
fun NavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: Destination = Destination.LoginScreen,
    route: String? = null
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.fullRoute,
        modifier = modifier,
        route = route
    ) {
        composable(destination = Destination.LoginScreen) {
            LoginScreen()
        }

        composable(destination = Destination.CartScreen) {
            CartScreen()
        }

        composable(destination = Destination.CatalogScreen) {
            CatalogScreen()
        }

        composable(destination = Destination.OrdersScreen) {
            OrdersContainerScreen()
        }

        composable(destination = Destination.SearchScreen) {
            SearchScreen()
        }

        composable(destination = Destination.STOScreen) {
            STOScreen()
        }

        composable(destination = Destination.SearchResultsScreen) {
            SearchResultsScreen()
        }
    }
}

fun NavGraphBuilder.composable(
    destination: Destination,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = destination.fullRoute,
        arguments = arguments,
        deepLinks = deepLinks,
        content = content
    )
}