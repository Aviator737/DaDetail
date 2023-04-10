package ru.dadetail.ui.common

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.dadetail.R
import ru.dadetail.ui.theme.AppTheme
import ru.dadetail.ui.theme.defaultElementsShape
import ru.dadetail.ui.theme.defaultInputHeight

@Composable
fun Input(
    modifier: Modifier = Modifier,
    hint: String = "",
    text: TextFieldValue = TextFieldValue(""),
    icon: Painter? = null,
    type: InputType = InputType.TEXT,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    endIcon: Painter? = null,
    onInput: (TextFieldValue) -> Unit = {},
) {
    val colors = AppTheme.colors
    var outlineColor by remember { mutableStateOf(colors.gray) }
    var backgroundColor by remember { mutableStateOf(colors.transparent) }
    var passwordVisible by remember { mutableStateOf(false) }

    val showPasswordImg = painterResource(R.drawable.ic_eye_on)
    val hidePasswordImg = painterResource(R.drawable.ic_eye_off)

    BasicTextField(
        textStyle = TextStyle(color = colors.labelPrimary, fontSize = 14.sp),
        cursorBrush = SolidColor(colors.labelPrimary),
        value = text,
        onValueChange = { onInput(it) },
        visualTransformation = if(type == InputType.PASSWORD && !passwordVisible)
            PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = keyboardOptions,
        modifier = modifier
            .onFocusChanged {
                if (it.hasFocus) {
                    outlineColor = colors.gray4
                    backgroundColor = colors.backgroundElevatedSecondary
                } else {
                    outlineColor = colors.gray6
                    backgroundColor = colors.backgroundElevatedSecondary
                }
            }
            .fillMaxWidth()
            .defaultMinSize(minHeight = defaultInputHeight),
        decorationBox = { innerTextField ->
            Row (
                Modifier
                    .background(backgroundColor, defaultElementsShape)
                    .border(BorderStroke(2.dp, outlineColor), defaultElementsShape)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                icon?.let {
                    Image(
                        painter = it,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .size(16.dp)
                    )
                }
                Box(modifier = Modifier.padding(start = 10.dp).fillMaxWidth()) {
                    if (text.text.isEmpty()) {
                        Text(hint, color = colors.labelSecondary)
                    }
                    if (type == InputType.PASSWORD) {
                        Image(
                            painter = if (passwordVisible) hidePasswordImg else showPasswordImg,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(start = 16.dp, end = 20.dp)
                                .align(Alignment.CenterEnd)
                                .clickable { passwordVisible = !passwordVisible }
                                .size(20.dp)
                        )
                    }
                    if (type != InputType.PASSWORD && endIcon != null) {
                        Image(
                            painter = endIcon,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(start = 16.dp, end = 20.dp)
                                .align(Alignment.CenterEnd)
                                .size(20.dp)
                        )
                    }
                    if (isLoading)
                        CircularProgressIndicator(
                            color =  AppTheme.colors.gray,
                            strokeWidth = 2.dp,
                            modifier = Modifier
                                .padding(start = 16.dp, end = 20.dp)
                                .align(Alignment.CenterEnd)
                                .size(14.dp)
                        )
                    Box(Modifier.padding(end = if (type == InputType.PASSWORD) 48.dp else 16.dp)) {
                        innerTextField()
                    }
                }
            }
        },
        enabled = enabled
    )
}

@Composable
@Preview
private fun InputPreview() {
    Input()
}

enum class InputType {
    TEXT, PASSWORD
}