package com.kakyiretechnologies.chatioandroid.di

import com.kakyiretechnologies.chatioandroid.data.AuthenticationInterceptor
import com.kakyiretechnologies.chatioandroid.data.api.ApiService
import com.kakyiretechnologies.chatioandroid.data.api.socketio.SocketIOUtils
import com.kakyiretechnologies.chatioandroid.utils.BASE_URL
import com.kakyiretechnologies.chatioandroid.utils.OK_HTTP_TIMEOUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 23, 2022.
 * https://github.com/kakyire
 */

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideApiService(okhttpClient: OkHttpClient): ApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okhttpClient)
        .build()
        .create()


    @Provides
    fun provideOkHttpClient(authenticationInterceptor: AuthenticationInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor { authenticationInterceptor.intercept(it) }
            .connectTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideSocketIO(): SocketIOUtils {
        return SocketIOUtils(url = BASE_URL)
    }
}