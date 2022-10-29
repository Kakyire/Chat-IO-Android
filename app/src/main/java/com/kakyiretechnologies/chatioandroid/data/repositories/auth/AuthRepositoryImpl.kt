package com.kakyiretechnologies.chatioandroid.data.repositories.auth

import com.kakyiretechnologies.chatioandroid.data.api.ApiService
import com.kakyiretechnologies.chatioandroid.data.model.request.LoginRequest
import com.kakyiretechnologies.chatioandroid.data.model.request.SignUpRequest
import com.kakyiretechnologies.chatioandroid.data.model.response.ApiResponse
import com.kakyiretechnologies.chatioandroid.data.model.response.UserResponse
import com.kakyiretechnologies.chatioandroid.utils.DataState
import com.kakyiretechnologies.chatioandroid.utils.makeNetworkCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 23, 2022.
 * https://github.com/kakyire
 */
class AuthRepositoryImpl @Inject constructor(private val apiService: ApiService) : AuthRepository {
    override fun login(loginRequest: LoginRequest): Flow<DataState<ApiResponse<UserResponse>>> {
        return makeNetworkCall { apiService.login(loginRequest) }
    }

    override fun signUp(signUpRequest: SignUpRequest): Flow<DataState<ApiResponse<UserResponse>>> {
        return makeNetworkCall { apiService.signUp(signUpRequest) }
    }
}