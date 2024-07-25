package com.jobkorea.app.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
fun dpToPx(dp: Dp): Int {
    val density = LocalDensity.current
    return with(density) { dp.toPx().toInt() }
}
