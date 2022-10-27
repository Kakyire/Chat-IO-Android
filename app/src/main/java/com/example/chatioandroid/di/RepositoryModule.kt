package com.example.chatioandroid.di

import com.example.chatioandroid.data.repositories.auth.AuthRepository
import com.example.chatioandroid.data.repositories.auth.AuthRepositoryImpl
import com.example.chatioandroid.data.repositories.user.UserRepository
import com.example.chatioandroid.data.repositories.user.UserRepositoryImpl
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

    @Binds
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl):UserRepository
}