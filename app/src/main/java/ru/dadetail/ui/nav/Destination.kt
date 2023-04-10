package ru.dadetail.ui.nav

sealed class Destination(protected val route: String, vararg params: String) {
    val fullRoute: String = if (params.isEmpty()) route else {
        val builder = StringBuilder(route)
        params.forEach { builder.append("/{${it}}") }
        builder.toString()
    }

    sealed class NoArgumentsDestination(route: String) : Destination(route) {
        operator fun invoke(): String = route
    }

    object LoginScreen : NoArgumentsDestination(Screens.LOGIN_SCREEN)

    object WelcomeScreen : NoArgumentsDestination(Screens.WELCOME_SCREEN)

    object CartScreen : NoArgumentsDestination(Screens.CART_SCREEN)

    object CatalogScreen : NoArgumentsDestination(Screens.CATALOG_SCREEN)

    object OrdersScreen : NoArgumentsDestination(Screens.ORDERS_SCREEN)

    object PositionsScreen : NoArgumentsDestination(Screens.POSITIONS_SCREEN)

    object SearchScreen : NoArgumentsDestination(Screens.SEARCH_SCREEN)

    object STOScreen : NoArgumentsDestination(Screens.STO_SCREEN)

    object SearchResultsScreen : Destination(Screens.SEARCH_RESULTS_SCREEN, Args.ARTICLE, Args.BRAND) {
        operator fun invoke(article: String, brand: String): String = route.appendParams(
            Args.ARTICLE to article,
            Args.BRAND to brand
        )
    }

    object Args {
        const val ARTICLE = "article"
        const val BRAND = "brand"
    }

    object Screens {
        const val LOGIN_SCREEN = "login"

        const val WELCOME_SCREEN = "welcome"
        const val ORDERS_SCREEN = "orders"
        const val STO_SCREEN = "sto"
        const val SEARCH_SCREEN = "search"
        const val CATALOG_SCREEN = "catalog"
        const val CART_SCREEN = "cart"
        const val POSITIONS_SCREEN = "positions"
        const val SEARCH_RESULTS_SCREEN = "search_results"
    }
}

internal fun String.appendParams(vararg params: Pair<String, Any?>): String {
    val builder = StringBuilder(this)

    params.forEach {
        it.second?.toString()?.let { arg ->
            builder.append("/$arg")
        }
    }

    return builder.toString()
}