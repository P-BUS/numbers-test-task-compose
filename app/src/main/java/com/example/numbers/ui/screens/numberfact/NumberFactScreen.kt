package com.example.numbers.ui.screens.numberfact

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.numbers.ui.screens.components.NavigateBackIcon

@Composable
fun NumberFactScreen(
    modifier: Modifier = Modifier,
    number: String,
    fact: String,
    navigateBack: () -> Unit,
    viewModel: NumberFactViewModel
) {
    Box(
        modifier = Modifier.fillMaxSize()
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
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(text = number, maxLines = 1)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = fact)
    }
}