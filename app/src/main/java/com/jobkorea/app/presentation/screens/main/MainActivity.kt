package com.jobkorea.app.presentation.screens.main

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.jobkorea.app.data.ScreenParams
import com.jobkorea.app.presentation.screens.popup.PopupScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "home",
                modifier = Modifier.fillMaxSize(),
                route = "root",
            ) {
                composable("home") {
                    MainScreen(navController)
                }
                composable<ScreenParams> { entry ->
                    val screenParams = entry.toRoute<ScreenParams>()
                    PopupScreen(screenParams)
                }
            }


        }


    }


}





