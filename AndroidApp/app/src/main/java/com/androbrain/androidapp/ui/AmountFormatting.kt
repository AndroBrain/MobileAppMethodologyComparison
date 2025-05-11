package com.androbrain.androidapp.ui

fun Long.toAmount() = "${this / 100},${this % 100} zł"