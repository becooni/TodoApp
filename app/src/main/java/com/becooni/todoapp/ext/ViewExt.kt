package com.becooni.todoapp.ext

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

object ViewExt {

    @JvmStatic
    @BindingAdapter("isVisible")
    fun View.isVisible(visible: Boolean) {
        isVisible = visible
    }
}