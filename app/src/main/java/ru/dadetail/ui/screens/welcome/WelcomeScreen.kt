package ru.dadetail.ui.screens.welcome

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel

@Composable
fun WelcomeScreen(
    viewModel: WelcomeViewModel = koinViewModel()
) {
    Text(text = "welcome")
}

@Preview
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen()
}