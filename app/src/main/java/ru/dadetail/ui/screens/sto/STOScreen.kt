package ru.dadetail.ui.screens.sto

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import org.koin.androidx.compose.koinViewModel
import ru.dadetail.R
import ru.dadetail.ui.common.EmptyStub
import ru.dadetail.ui.theme.AppTheme

@Composable
fun STOScreen(
    viewModel: STOViewModel = koinViewModel()
) {
    EmptyStub(
        modifier = Modifier.background(AppTheme.colors.backgroundSecondary),
        icon = painterResource(id = R.drawable.ic_catalog),
        primaryText = stringResource(id = R.string.label_empty_sto_primary),
        secondaryText = stringResource(id = R.string.label_empty_sto_secondary)
    )
}