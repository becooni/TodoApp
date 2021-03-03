package com.becooni.todoapp.extentions

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