package com.androbrain.crosscompileapp.ui

import kotlin.math.roundToInt

fun Long.toAmount() = "${this / 100},${this % 100} zł"

fun Double.roundToDecimals(decimals: Int): Double {
    var dotAt = 1
    repeat(decimals) { dotAt *= 10 }
    val roundedValue = (this * dotAt).roundToInt()
    return (roundedValue / dotAt) + (roundedValue % dotAt).toDouble() / dotAt
}
