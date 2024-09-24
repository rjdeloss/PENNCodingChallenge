package com.example.penncodingchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val scope = rememberCoroutineScope()

            NavHost(navController = navController, startDestination = "launch_screen") {
                composable("launch_screen") {
                    LaunchScreen()
                    LaunchedEffect(Unit) {
                        scope.launch {
                            delay(2000)
                            navController.navigate("main_screen") {
                                popUpTo("launch_screen") { inclusive = true }
                            }
                        }
                    }
                }
                composable("main_screen") {
                    MainScreen()
                }
            }

        }
    }
}