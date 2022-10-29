package com.kakyiretechnologies.chatioandroid.utils.livedata

import androidx.lifecycle.Observer


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 29, 2022.
 * https://github.com/kakyire
 */


class EventObserver <T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled()?.let { value ->
            onEventUnhandledContent(value)
        }
    }
}