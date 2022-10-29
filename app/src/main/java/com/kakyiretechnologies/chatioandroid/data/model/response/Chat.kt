package com.kakyiretechnologies.chatioandroid.data.model.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Chat(
    @SerializedName("_id")
    val id: String,
    val participants: List<Participant>,
    val createdAt: String,
    val updatedAt: String,
    @SerializedName("__v")
    val v: Int
)