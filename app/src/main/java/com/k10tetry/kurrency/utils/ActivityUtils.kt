package com.k10tetry.kurrency.utils

import android.app.Activity
import android.content.res.Resources
import android.widget.Toast

fun Activity.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(applicationContext, message, duration).show()
}

fun Int.toPx(resources: Resources): Int = this * resources.displayMetrics.density.toInt()