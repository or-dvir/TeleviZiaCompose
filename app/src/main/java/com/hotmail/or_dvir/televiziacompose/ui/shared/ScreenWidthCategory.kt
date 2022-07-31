package com.hotmail.or_dvir.televiziacompose.ui.shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun getScreenWidthCategory(): ScreenWidthCategory {
    val width = LocalConfiguration.current.screenWidthDp

    return when {
        width < 600 -> ScreenWidthCategory.SMALL
        width < 840 -> ScreenWidthCategory.MEDIUM
        else -> ScreenWidthCategory.LARGE
    }
}

enum class ScreenWidthCategory {
    SMALL,
    MEDIUM,
    LARGE
}