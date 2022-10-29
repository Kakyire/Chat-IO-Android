package com.kakyiretechnologies.chatioandroid.utils

import android.util.Patterns
import java.util.regex.Pattern


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 25, 2022.
 * https://github.com/kakyire
 */
object EmailValidation {

    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}