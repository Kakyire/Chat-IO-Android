package com.kakyiretechnologies.chatioandroid.data.model.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class UserResponse(
    @SerializedName("_id")
    val id: String,
    val email: String,
    val username: String,
    val createdAt: String,
    val updatedAt: String,
    val token: String
)