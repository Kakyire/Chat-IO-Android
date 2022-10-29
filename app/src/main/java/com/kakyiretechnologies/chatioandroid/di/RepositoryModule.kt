package com.kakyiretechnologies.chatioandroid.di

import com.kakyiretechnologies.chatioandroid.data.repositories.auth.AuthRepository
import com.kakyiretechnologies.chatioandroid.data.repositories.auth.AuthRepositoryImpl
import com.kakyiretechnologies.chatioandroid.data.repositories.chat.ChatRepository
import com.kakyiretechnologies.chatioandroid.data.repositories.chat.ChatRepositoryImpl
import com.kakyiretechnologies.chatioandroid.data.repositories.user.UserRepository
import com.kakyiretechnologies.chatioandroid.data.repositories.user.UserRepositoryImpl
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
    abstract fun  bindAuthRepository (authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    abstract  fun bindChatRepository(chatRepositoryImpl: ChatRepositoryImpl): ChatRepository
}