package com.becooni.todoapp.provider

import androidx.annotation.PluralsRes

interface ResourceProvider {

    fun getQuantityString(@PluralsRes id: Int, quantity: Int): String
}