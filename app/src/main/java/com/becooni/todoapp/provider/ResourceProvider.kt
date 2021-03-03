package com.becooni.todoapp.provider

import android.content.Context
import androidx.annotation.PluralsRes
import javax.inject.Inject

class ResourceProvider @Inject constructor(
    private val context: Context
) {

    fun getQuantityString(@PluralsRes id: Int, quantity: Int) =
        context.resources.getQuantityString(id, quantity, quantity)
}