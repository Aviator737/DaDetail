package ru.dadetail.ui.screens.auth.login

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import ru.dadetail.ui.theme.AppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginOptionTabs(
    modifier: Modifier = Modifier,
    tabs: List<LoginOption> = LoginOption.values().toList(),
    pagerState: PagerState = rememberPagerState(initialPage = 0),
    onTabClicked: (LoginOption) -> Unit = {}
) {
    val scope = rememberCoroutineScope()

    Box(modifier = modifier) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = AppTheme.colors.transparent,
            contentColor = AppTheme.colors.labelPrimary
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    text = { Text(text = stringResource(tab.title)) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                        onTabClicked(tab)
                    }
                )
            }
        }
        HorizontalPager(pageCount = LoginOption.values().size, state = pagerState) {}
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun LoginOptionTabsPreview() {
    LoginOptionTabs()
}