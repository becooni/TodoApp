package com.becooni.todoapp.provider

import android.content.Context
import androidx.annotation.PluralsRes

class ResourceProviderImpl(private val context: Context) : ResourceProvider {

    override fun getQuantityString(@PluralsRes id: Int, quantity: Int) =
        context.resources.getQuantityString(id, quantity, quantity)
}