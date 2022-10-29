package com.kakyiretechnologies.chatioandroid.application

import android.app.Application
import com.kakyiretechnologies.chatioandroid.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 23, 2022.
 * https://github.com/kakyire
 */
@HiltAndroidApp
class ChatIOApp :Application(){
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}