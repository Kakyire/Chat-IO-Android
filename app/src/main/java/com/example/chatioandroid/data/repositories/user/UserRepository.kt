package com.example.chatioandroid.data.repositories.user

import com.example.chatioandroid.data.model.response.ApiResponse
import com.example.chatioandroid.data.model.response.UserResponse
import com.example.chatioandroid.data.model.response.UsersListResponse
import com.example.chatioandroid.utils.DataState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Path


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 27, 2022.
 * https://github.com/kakyire
 */
interface UserRepository {
    fun getUserProfile(): Flow<DataState<ApiResponse<UserResponse>>>
    fun getUsers(): Flow<DataState<ApiResponse<UsersListResponse>>>
    fun getOtherUserProfile(id: String): Flow<DataState<ApiResponse<UserResponse>>>
}