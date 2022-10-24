package com.example.chatioandroid.di

import com.example.chatioandroid.data.repositories.auth.AuthRepository
import com.example.chatioandroid.data.repositories.auth.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 23, 2022.
 * https://github.com/kakyire
 */
@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun  bindAuthRepository (authRepositoryImpl: AuthRepositoryImpl):AuthRepository
}