package com.example.chatioandroid.utils

import java.lang.Exception


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 23, 2022.
 * https://github.com/kakyire
 */
sealed class DataState<out R> {
    class Success<T>(val data: T) : DataState<T>()
    class Error(val message: String) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}
