package com.becooni.todoapp.di

import com.becooni.todoapp.repository.DefaultTodoTodoRepository
import com.becooni.todoapp.repository.TodoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindTodoRepository(
        defaultTodoTodoRepository: DefaultTodoTodoRepository
    ): TodoRepository
}