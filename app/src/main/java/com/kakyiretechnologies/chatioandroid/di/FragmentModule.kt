package com.kakyiretechnologies.chatioandroid.di

import androidx.fragment.app.Fragment
import com.kakyiretechnologies.chatioandroid.utils.OnItemClickListener
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 27, 2022.
 * https://github.com/kakyire
 */
@Module
@InstallIn(FragmentComponent::class)
class FragmentModule {

    @Provides
    fun provideOnItemClickListener(fragment: Fragment) = fragment as OnItemClickListener
}