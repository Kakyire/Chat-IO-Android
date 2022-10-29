package com.kakyiretechnologies.chatioandroid.data.model.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.kakyiretechnologies.chatioandroid.data.model.response.Participant

@Keep
data class Message(
    @SerializedName("_id")
    val id: String,
    val sender: Participant,
    val `receiver`: Participant,
    val chatId: String,
    val text: String,
    val createdAt: String,
    val updatedAt: String,
    @SerializedName("__v")
    val v: Int
)