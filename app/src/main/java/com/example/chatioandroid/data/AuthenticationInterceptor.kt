package com.example.chatioandroid.data

import com.example.chatioandroid.preferences.Keys.ACCESS_TOKEN
import com.example.chatioandroid.preferences.PreferenceManager
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import javax.inject.Inject


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 26, 2022.
 * https://github.com/kakyire
 */
class AuthenticationInterceptor @Inject constructor(
    private val preferenceManager: PreferenceManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = preferenceManager.getString(ACCESS_TOKEN)

        val request = chain.request()
        val builder = request.newBuilder()

        builder.apply {
            addHeader("Content-Type", CONTENT_TYPE_JSON)
            addHeader("Accept", CONTENT_TYPE_JSON)
            addHeader("lang", "en")
            if (!token.isNullOrEmpty()) addHeader("x-access-token", token)
            build()
        }

        return chain.proceed(request)
    }

    companion object {
        private const val CONTENT_TYPE_JSON = "application/json"
    }
}
