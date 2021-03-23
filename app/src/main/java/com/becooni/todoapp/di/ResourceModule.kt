package com.becooni.todoapp.di

import android.content.Context
import com.becooni.todoapp.provider.ResourceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ResourceModule {

    @Provides
    @ViewModelScoped
    fun provideResourceProvider(@ApplicationContext appContext: Context) =
        ResourceProvider(appContext)
}