package ru.dadetail.ui.screens.bottom_nav

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.dadetail.ui.nav.AppNavigator
import ru.dadetail.ui.theme.AppTheme


@Composable
fun BottomNav(
    navController: NavController,
    appNavigator: AppNavigator
) {
    val items = listOf(
        BottomNavItem.Search,
        BottomNavItem.STO,
        BottomNavItem.Catalog,
        BottomNavItem.Cart,
        BottomNavItem.Orders
    )
    BottomNavigation(
        backgroundColor = AppTheme.colors.bottomNav,
        contentColor = AppTheme.colors.labelPrimary
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(painter = painterResource(id = item.icon), contentDescription = stringResource(item.title))
                },
                label = {
                    Text(
                        text = stringResource(id = item.title),
                        fontSize = 10.sp,
                        fontWeight = FontWeight(600)
                    )
                },
                selectedContentColor = AppTheme.colors.primary,
                unselectedContentColor = AppTheme.colors.forceWhitePrimary,
                alwaysShowLabel = true,
                selected = isCurrentRoute(currentRoute, item.screenRoute),
                onClick = {
                    appNavigator.tryNavigateTo(item.screenRoute, isSingleTop = true)
                }
            )
        }
    }
}

fun isCurrentRoute(currentRoute: String?, screenRoute: String): Boolean {
    return currentRoute == screenRoute
//    return when {
//        currentRoute == MainNavigation.Routes.ORDERS_ROUTE &&
//                screenRoute == MainNavigation.Routes.POSITIONS_ROUTE -> true
//        else -> currentRoute == screenRoute
//    }
}