package com.kakyiretechnologies.chatioandroid.data.model.response


import androidx.annotation.Keep

@Keep
data class MessagesListResponse(
    val messages: List<Message>,
    val count: Int
)