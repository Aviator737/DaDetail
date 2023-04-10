package ru.dadetail.ui.screens.bottom_nav

import ru.dadetail.R
import ru.dadetail.ui.nav.Destination

sealed class BottomNavItem(val title: Int, val icon: Int, val screenRoute: String) {
    object Search: BottomNavItem(
        title = R.string.label_bottom_nav_search,
        icon = R.drawable.ic_search,
        screenRoute = Destination.Screens.SEARCH_SCREEN
    )
    object STO: BottomNavItem(
        title = R.string.label_bottom_nav_sto,
        icon = R.drawable.ic_sto,
        screenRoute = Destination.Screens.STO_SCREEN
    )
    object Catalog: BottomNavItem(
        title = R.string.label_bottom_nav_catalog,
        icon = R.drawable.ic_catalog,
        screenRoute = Destination.Screens.CATALOG_SCREEN
    )
    object Cart: BottomNavItem(
        title = R.string.label_bottom_nav_cart,
        icon = R.drawable.ic_trash,
        screenRoute = Destination.Screens.CART_SCREEN
    )
    object Orders: BottomNavItem(
        title = R.string.label_bottom_nav_orders,
        icon = R.drawable.ic_orders,
        screenRoute = Destination.Screens.ORDERS_SCREEN
    )
}