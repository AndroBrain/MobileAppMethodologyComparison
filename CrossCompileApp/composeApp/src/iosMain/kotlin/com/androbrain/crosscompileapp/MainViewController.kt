package com.androbrain.crosscompileapp

import androidx.compose.ui.window.ComposeUIViewController
import com.androbrain.crosscompileapp.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }
