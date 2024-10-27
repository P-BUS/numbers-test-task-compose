package com.example.numbers.ui.screens.numberfact

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.numbers.ui.screens.components.NavigateBackIcon

@Composable
fun NumberFactScreen(
    number: String,
    fact: String,
    navigateBack: () -> Unit,
    viewModel: NumberFactViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(horizontal = 20.dp)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        NavigateBackIcon(
            modifier = Modifier
                .align(Alignment.TopStart),
            onBackIconClicked = navigateBack
        )
        FactSection(
            modifier = Modifier
                .align(Alignment.Center),
            number = number,
            fact = fact,
        )
    }
}

@Composable
fun FactSection(
    modifier: Modifier = Modifier,
    number: String,
    fact: String,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = number,
            maxLines = 1,
            style = MaterialTheme.typography.headlineLarge.copy(
                color = Color.White,
                fontSize = 32.sp
            )
        )
        Spacer(modifier = Modifier.width(40.dp))
        Text(
            text = fact,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium.copy(color = Color.LightGray)
        )
    }
}