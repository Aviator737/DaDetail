package ru.dadetail.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import ru.dadetail.ui.nav.AppNavigator
import ru.dadetail.ui.nav.NavHost
import ru.dadetail.ui.nav.NavigationEffects
import ru.dadetail.ui.screens.bottom_nav.BottomNav
import ru.dadetail.ui.theme.AppTheme

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
    mainViewModel: MainViewModel = koinViewModel(),
    appNavigator: AppNavigator = koinInject()
) {
    NavigationEffects(
        navigationFlow = mainViewModel.navigationFlow,
        navHostController = navController
    )
    AppTheme {
        Scaffold(
            bottomBar = {
                BottomNav(
                    navController = navController,
                    appNavigator = appNavigator
                )
            },
        ) { innerPadding ->
            Box(Modifier.padding(innerPadding)) {
                NavHost(navController = navController)
            }
        }
    }
}