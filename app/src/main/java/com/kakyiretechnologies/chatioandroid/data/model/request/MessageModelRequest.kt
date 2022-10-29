package com.kakyiretechnologies.chatioandroid.data.model.request

import androidx.annotation.Keep


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 28, 2022.
 * https://github.com/kakyire
 */
@Keep
data class MessageModelRequest(val receiverId:String,val text:String)
