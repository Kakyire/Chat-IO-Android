package com.kakyiretechnologies.chatioandroid.data.api.socketio

import com.google.gson.Gson
import io.socket.client.IO
import io.socket.client.Socket
import timber.log.Timber


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 29, 2022.
 * https://github.com/kakyire
 */
@Suppress("UNCHECKED_CAST")
class SocketIOUtils(url: String, private val gson: Gson = Gson()) {

    private val io: Socket = IO.socket(url)


    fun connect(): Socket? {
        return io.connect()
    }

    fun disconnect(): Socket? {
        return io.disconnect()
    }


    fun <T> sendEvent(event: String, message: T) {
        if (!io.connected()) throw IllegalAccessException("Socket IO is not connected")

        io.emit(event, gson.toJson(message))
    }

    fun sendEvent(event: String, message: String) {
        if (!io.connected()) throw IllegalAccessException("Socket IO is not connected")

        io.emit(event, message)
    }


    fun onConnect() {
        io.apply {
            on("connect") {
                Timber.d("Connected to server with id: ${id()}")
            }
        }
    }

    fun onEventReceive(event: String){
        io.on(event){

              Timber.tag(TAG).d("results ${it[0]}")

        }
    }
    fun <T : Any> onEventReceive(event: String, clazz: Class<T>, onReceived: (T) -> Unit) {
        io.on(event) {
            if (event.equals(
                    "connect",
                    true
                )
            ) {
                throw IllegalArgumentException("Use onConnect function to check connection")
            }
            if (it.isNotEmpty()) {
                val value = gson.fromJson(it[0].toString(), clazz)
                onReceived(value)
            }
        }
    }


}

private const val TAG = "SocketIOUtils"