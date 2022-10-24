package com.example.chatioandroid.data.repositories.auth

import com.example.chatioandroid.data.api.ApiService
import com.example.chatioandroid.data.model.request.LoginRequest
import com.example.chatioandroid.data.model.request.SignUpRequest
import com.example.chatioandroid.data.model.response.ApiResponse
import com.example.chatioandroid.data.model.response.AuthResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 23, 2022.
 * https://github.com/kakyire
 */
class AuthRepositoryImpl @Inject constructor(private val apiService: ApiService) : AuthRepository {
    override fun login(loginRequest: LoginRequest) = flow { emit(apiService.login(loginRequest)) }

    override fun signUp(signUpRequest: SignUpRequest) =
        flow { emit(apiService.signUp(signUpRequest)) }
}