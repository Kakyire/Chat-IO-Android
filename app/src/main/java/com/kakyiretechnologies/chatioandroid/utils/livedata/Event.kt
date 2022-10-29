package com.kakyiretechnologies.chatioandroid.utils.livedata


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 29, 2022.
 * https://github.com/kakyire
 */

open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set

    //Returns the content and prevents its use again.
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }


    fun peekContent(): T = content

}