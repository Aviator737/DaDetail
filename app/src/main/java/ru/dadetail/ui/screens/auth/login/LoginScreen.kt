package ru.dadetail.ui.screens.auth.login

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.koinViewModel
import org.koin.core.context.GlobalContext
import ru.dadetail.R
import ru.dadetail.di.appModule
import ru.dadetail.ui.base.SIDE_EFFECTS_KEY
import ru.dadetail.ui.common.*
import ru.dadetail.ui.theme.AppTheme
import ru.dadetail.ui.theme.bottomSheetShape


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel()
) {
    val state = viewModel.uiState.collectAsState()

    var loginHintId = -1
    var loginText = TextFieldValue()
    var loginKeyboardType = KeyboardType.Text

    when(state.value.loginOption) {
        LoginOption.LOGIN -> {
            loginHintId = R.string.label_login
            loginText = state.value.login
            loginKeyboardType = KeyboardType.Text
        }
        LoginOption.EMAIL -> {
            loginHintId = R.string.label_email
            loginText = state.value.email
            loginKeyboardType = KeyboardType.Email
        }
        LoginOption.PHONE -> {
            loginHintId = R.string.label_phone
            loginText = state.value.phone
            loginKeyboardType = KeyboardType.Phone
        }
    }

    fun setLogin(value: TextFieldValue) {
        when(state.value.loginOption) {
            LoginOption.LOGIN -> viewModel.setEvent(LoginContract.Event.OnLoginInput(value))
            LoginOption.EMAIL -> viewModel.setEvent(LoginContract.Event.OnEmailInput(value))
            LoginOption.PHONE -> viewModel.setEvent(LoginContract.Event.OnPhoneInput(value))
        }
    }

    Box {
        CompanyGradient()
        Column {
            Logo(modifier = Modifier.padding(top = 26.dp, start = 30.dp, bottom = 19.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(AppTheme.colors.backgroundSecondary, bottomSheetShape),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = stringResource(id = R.string.label_enter_to_app),
                        modifier = Modifier.padding(start = 16.dp, top = 30.dp),
                        fontSize = 22.sp,
                        color = AppTheme.colors.labelPrimary
                    )
                    LoginOptionTabs(modifier = Modifier.padding(top = 16.dp)) {
                        viewModel.setEvent(LoginContract.Event.OnLoginOptionTabChanged(it))
                    }
                    Input(
                        modifier = Modifier.padding(top = 4.dp),
                        hint = stringResource(loginHintId),
                        text = loginText,
                        keyboardOptions = KeyboardOptions(
                            autoCorrect = false,
                            keyboardType = loginKeyboardType,
                            imeAction = ImeAction.Next
                        ),
                        onInput = { setLogin(it) }
                    )
                    Input(
                        modifier = Modifier.padding(top = 2.dp),
                        hint = stringResource(id = R.string.label_password),
                        text = state.value.password,
                        type = InputType.PASSWORD,
                        keyboardOptions = KeyboardOptions(
                            autoCorrect = false,
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Go
                        ),
                        onInput = {
                            viewModel.setEvent(LoginContract.Event.OnPasswordInput(it))
                        }
                    )
                }
                Button(
                    modifier = Modifier.height(74.dp),
                    text = stringResource(id = R.string.label_auth),
                    fontSize = 22.sp,
                    isEnabled = !state.value.isLoading &&
                                loginText.text.isNotEmpty() &&
                                state.value.password.text.isNotEmpty(),
                    isLoading = state.value.isLoading
                ) {
                    viewModel.setEvent(LoginContract.Event.OnDoLogin)
                }
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    GlobalContext.startKoin {
        modules(appModule)
    }
    LoginScreen()
}