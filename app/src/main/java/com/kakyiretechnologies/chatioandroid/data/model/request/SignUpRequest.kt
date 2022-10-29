package com.kakyiretechnologies.chatioandroid.data.model.request

import androidx.annotation.Keep


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 23, 2022.
 * https://github.com/kakyire
 */
@Keep
data class SignUpRequest(val username: String = "", val email: String, val password: String)
