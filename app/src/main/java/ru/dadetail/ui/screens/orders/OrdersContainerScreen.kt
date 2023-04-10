package ru.dadetail.ui.screens.orders

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.dadetail.R
import ru.dadetail.ui.nav.Destination
import ru.dadetail.ui.theme.AppTheme
import ru.dadetail.ui.theme.bottomSheetShape

@Composable
fun OrdersContainerScreen() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            modifier = Modifier
                .height(138.dp)
                .fillMaxWidth()
                .padding(start = 34.dp, end = 34.dp, bottom = 34.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.label_nav_positions),
                    //modifier = Modifier.clickable { navigateToPositions() },
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W600,
                    //color = if (currentRoute == Destination.Screens.POSITIONS_SCREEN) AppTheme.colors.labelPrimary else AppTheme.colors.labelTertiary
                )
                Text(
                    text = stringResource(id = R.string.label_bottom_nav_orders),
                    //modifier = Modifier.clickable { navigateToOrders() },
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W600,
                    //color = if (currentRoute == Destination.Screens.ORDERS_SCREEN) AppTheme.colors.labelPrimary else AppTheme.colors.labelTertiary
                )
            }
        }
        Box(modifier = Modifier.background(AppTheme.colors.backgroundSecondary, bottomSheetShape)) {
            //screen()
        }
    }
}