package com.jobkorea.app.presentation.screens.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jobkorea.app.R
import com.jobkorea.app.presentation.screens.main.MainActivity
import com.jobkorea.app.presentation.theme.JkcomposeTheme
import com.jobkorea.app.viewmodel.SplashUiState
import com.jobkorea.app.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : ComponentActivity() {

    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JkcomposeTheme {
               SplashScreen (onTimeOut = {
                   startActivity(Intent(this, MainActivity::class.java))
                   finish()
               })
            }
        }


        /* 딥링크 체크 */
        intent?.data?.let {
            splashViewModel.checkDeepLink(it)
        }

    }
}

@Composable
fun SplashScreen(onTimeOut: () -> Unit) {

    val viewModel = hiltViewModel<SplashViewModel>()
    var isTimeout by remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        viewModel.fetchSplashImage()
        delay(3000)
        isTimeout = true
    }

    if (isTimeout) {
        onTimeOut()
        return
    }

    val uiState by viewModel.uiState.collectAsState()

    when(uiState) {
        null -> {}
        is SplashUiState.ShowAdvertise -> {
            Text(text = (uiState as SplashUiState.ShowAdvertise).responseSplashAdvertise.toString())
        }
        is SplashUiState.ShowDefaultSplash -> {
            DefaultSplashScreen()
        }
    }
}

@Composable
fun DefaultSplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        CenterImage()
        BottomCenterImage()
    }
}

@Composable
fun CenterImage() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.splash_default_logo),
            contentDescription = "Center Image",
            modifier = Modifier.size(256.dp)
        )
    }
}

@Composable
fun BottomCenterImage() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.splash_default_character),
            contentDescription = "Bottom Center Image",
            modifier = Modifier.size(300.dp).padding(64.dp)
        )
    }
}

