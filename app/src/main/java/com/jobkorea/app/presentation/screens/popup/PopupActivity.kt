package com.jobkorea.app.presentation.screens.popup

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.gson.Gson
import com.jobkorea.app.data.ActivityParams
import com.jobkorea.app.presentation.viewmodel.MainEvent
import com.jobkorea.app.presentation.viewmodel.PopupViewModel
import com.jobkorea.app.presentation.viewmodel.PopupViewModel.*
import kotlinx.coroutines.launch

class PopupActivity : ComponentActivity() {

    private val viewModel by viewModels<PopupViewModel>()

    companion object {
        const val PARAMS = "params"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val jsonParams = intent.getStringExtra(PARAMS)
        val params = jsonParams?.let { Gson().fromJson(it, ActivityParams::class.java) }

        setContent {
            PopupScreen(screenParams = params)
        }

        setObservers()
    }

    private fun setObservers() {

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.event.collect {
                    when (it) {
                        is PopupEvent.GoActivity -> Intent(
                            this@PopupActivity,
                            PopupActivity::class.java
                        ).apply {
                            putExtra(PARAMS, Gson().toJson(it.params))
                        }.also { intent ->
                            startActivity(intent)
                        }
                    }
                }
            }
        }
    }
}