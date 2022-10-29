package com.kakyiretechnologies.chatioandroid.data


import com.kakyiretechnologies.chatioandroid.preferences.Keys.ACCESS_TOKEN
import com.kakyiretechnologies.chatioandroid.preferences.PreferenceManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class AuthenticationInterceptor @Inject constructor(
    private val preferenceManager: PreferenceManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = preferenceManager.getString(ACCESS_TOKEN)

        val request = chain.request().newBuilder()
            .apply {
                addHeader("Content-Type",
                    com.kakyiretechnologies.chatioandroid.data.AuthenticationInterceptor.Companion.CONTENT_TYPE_JSON
                )
                addHeader("Accept",
                    com.kakyiretechnologies.chatioandroid.data.AuthenticationInterceptor.Companion.CONTENT_TYPE_JSON
                )
                addHeader("lang", "en")
                if (token != null) addHeader("x-access-token", token)
            }
            .build()
        return chain.proceed(request)
    }

    companion object {
        private const val CONTENT_TYPE_JSON = "application/json"
    }
}
