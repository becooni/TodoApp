package com.becooni.todoapp.di

import com.becooni.todoapp.persistence.TodoDao
import com.becooni.todoapp.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideTodoRepository(todoDao: TodoDao) = TodoRepository(todoDao)
}