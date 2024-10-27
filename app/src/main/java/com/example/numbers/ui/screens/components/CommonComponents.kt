package com.example.numbers.ui.screens.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.core.text.isDigitsOnly

@Preview
@Composable
fun PreviewBaseOutlinedButton() {
    BaseOutlinedButton({}, "Get fact")
}

@Composable
fun BaseOutlinedButton(
    onButtonClick: () -> Unit,
    buttonText: String,
    modifier: Modifier = Modifier,
    isButtonEnabled: Boolean = true,
    containerColor: Color = Color.White,
    disabledContainerColor: Color = Color.LightGray,
    textColor: Color = Color.Black,
    textColorDisabled: Color = Color.Black,
    radius: Dp = 4.dp,
    borderColor: Color = Color.Transparent,
    textStyle: TextStyle = MaterialTheme.typography.labelLarge,
) {
    OutlinedButton(
        shape = RoundedCornerShape(radius),
        border = BorderStroke(1.dp, borderColor),
        colors = ButtonDefaults.buttonColors(
            contentColor = textColor,
            disabledContentColor = textColorDisabled,
            containerColor = containerColor,
            disabledContainerColor = disabledContainerColor
        ),
        enabled = isButtonEnabled,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        onClick = { onButtonClick() }
    ) {
        Text(
            text = buttonText,
            style = textStyle,
            color = textColor
        )
    }
}

@Composable
fun DefaultInputField(
    modifier: Modifier = Modifier,
    text: String = "",
    textHint: String = "",
    keyboardType: KeyboardType = KeyboardType.Number,
    imeAction: ImeAction = ImeAction.Done,
    forceError: Boolean = false,
    errorText: String = "",
    supportText: String = "",
    onInputComplete: (String) -> Unit = {},
    onValueChange: (String) -> Unit = {},
    maxLines: Int = 1,
) {
    var editTextInput by remember(text) { mutableStateOf(text) }
    val focusManager = LocalFocusManager.current
    var wasFocused by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = editTextInput,
        onValueChange = { filledText ->
            if (keyboardType == KeyboardType.Number) {
                // Restrict anything else
                if (!filledText.isDigitsOnly()) {
                    return@OutlinedTextField
                }
            }

            editTextInput = filledText.also(onValueChange)
        },
        modifier = modifier
            .onFocusChanged {
                if (!it.isFocused && wasFocused) {
                    onInputComplete(editTextInput)
                }
                wasFocused = it.isFocused
            },
        textStyle = MaterialTheme.typography.bodyLarge,
        label = {
            Text(
                text = textHint,
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(
            onDone = {
//                onInputComplete(editTextInput)
                focusManager.clearFocus()
            }
        ),
        singleLine = true,
        shape = RoundedCornerShape(4.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            errorTextColor = Color.White,

            focusedContainerColor = Color.White.copy(alpha = 0.1f),
            unfocusedContainerColor = Color.White.copy(alpha = 0.1f),
            errorContainerColor = Color.White.copy(alpha = 0.1f),

            focusedBorderColor = Color.Blue,
            unfocusedBorderColor = Color.Transparent,
            errorBorderColor = Color.Red,

            focusedLabelColor = Color.White.copy(alpha = 0.5f),
            unfocusedLabelColor = Color.White.copy(alpha = 0.5f),
            errorLabelColor = Color.Red,

            errorSupportingTextColor = Color.Red,
            focusedSupportingTextColor = Color.White,
            unfocusedSupportingTextColor = Color.White,

            cursorColor = Color.Blue
        ),
        isError = errorText.isNotEmpty() or forceError,
        supportingText = {
            val extraText = errorText.ifEmpty { supportText }
            if (extraText.isNotEmpty()) {
                Text(text = extraText)
            }
        },
        maxLines = maxLines,
    )
}

@Composable
fun FactItem(
    modifier: Modifier = Modifier,
    number: String,
    fact: String,
    onItemClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick() }
    ) {
        Text(text = number, maxLines = 1)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = fact, maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
}

@Composable
fun NavigateBackIcon(
    modifier: Modifier = Modifier,
    onBackIconClicked: () -> Unit,
    containerColor: Color = Color.Transparent,
    iconColor: Color = Color.Black,
) {
    Surface(
        modifier = modifier
            .size(32.dp)
            .clickable { onBackIconClicked() },
        color = containerColor,
        shape = CircleShape
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "back",
            tint = iconColor,
            modifier = Modifier
                .padding(6.dp)
        )
    }
}

