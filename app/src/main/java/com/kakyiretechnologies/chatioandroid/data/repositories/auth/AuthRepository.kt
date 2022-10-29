package com.kakyiretechnologies.chatioandroid.data.repositories.auth


import com.kakyiretechnologies.chatioandroid.data.model.request.LoginRequest
import com.kakyiretechnologies.chatioandroid.data.model.request.SignUpRequest
import com.kakyiretechnologies.chatioandroid.data.model.response.ApiResponse
import com.kakyiretechnologies.chatioandroid.data.model.response.UserResponse
import com.kakyiretechnologies.chatioandroid.utils.DataState
import kotlinx.coroutines.flow.Flow


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 23, 2022.
 * https://github.com/kakyire
 */
interface AuthRepository {

    fun login(loginRequest: LoginRequest): Flow<DataState<ApiResponse<UserResponse>>>
    fun signUp(signUpRequest: SignUpRequest): Flow<DataState<ApiResponse<UserResponse>>>
}