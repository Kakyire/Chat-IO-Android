package com.kakyiretechnologies.chatioandroid.data.model.response


import androidx.annotation.Keep

@Keep
data class UsersListResponse(
    val users: List<UserResponse>,
    val count: Int
)