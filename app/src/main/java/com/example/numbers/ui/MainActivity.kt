package com.example.numbers.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.numbers.ui.navigation.AppNavigation
import com.example.numbers.ui.theme.NumbersTheme
import dagger.hilt.EntryPoint

@EntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NumbersTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NumbersApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun NumbersApp(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    AppNavigation(
        modifier = modifier,
        navController = navController,
    )
}