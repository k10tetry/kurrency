package com.k10tetry.kurrency.utils

fun String.capitalisation(): String {
    val firstLetter = this.substring(0, 1).uppercase()
    val remaining = this.substring(1).lowercase()
    return firstLetter.plus(remaining)
}