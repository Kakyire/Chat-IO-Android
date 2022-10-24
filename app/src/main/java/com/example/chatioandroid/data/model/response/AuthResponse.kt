package com.example.chatioandroid.data.model.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class AuthResponse(
    @SerializedName("_id")
    val id: String,
    val email: String,
    val username: String,
    val token: String
)