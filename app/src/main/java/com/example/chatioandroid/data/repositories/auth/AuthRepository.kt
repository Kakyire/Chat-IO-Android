package com.example.chatioandroid.data.repositories.auth

import com.example.chatioandroid.data.model.request.LoginRequest
import com.example.chatioandroid.data.model.request.SignUpRequest
import com.example.chatioandroid.data.model.response.ApiResponse
import com.example.chatioandroid.data.model.response.AuthResponse
import kotlinx.coroutines.flow.Flow


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 23, 2022.
 * https://github.com/kakyire
 */
interface AuthRepository {

    fun login(loginRequest: LoginRequest): Flow<ApiResponse<AuthResponse>>
    fun signUp(signUpRequest: SignUpRequest): Flow<ApiResponse<AuthResponse>>
}