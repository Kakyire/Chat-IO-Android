package com.example.chatioandroid.data.api

import com.example.chatioandroid.data.model.request.LoginRequest
import com.example.chatioandroid.data.model.request.SignUpRequest
import com.example.chatioandroid.data.model.response.ApiResponse
import com.example.chatioandroid.data.model.response.AuthResponse
import com.example.chatioandroid.data.model.response.UserResponse
import com.example.chatioandroid.data.model.response.UsersListResponse
import com.example.chatioandroid.utils.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 23, 2022.
 * https://github.com/kakyire
 */
interface ApiService {

    @POST(LOGIN_ROUTE)
    suspend fun login(@Body loginRequest: LoginRequest): Response<ApiResponse<AuthResponse>>

    @POST(SIGNUP_ROUTE)
    suspend fun signUp(@Body signUpRequest: SignUpRequest): Response<ApiResponse<AuthResponse>>

    @GET(MY_PROFILE_ROUTE)
    suspend fun getUserProfile(): Response<ApiResponse<UserResponse>>

    @GET(OTHERS_PROFILE_ROUTE)
    suspend fun getOtherUserProfile(@Path("id")  id: String): Response<ApiResponse<UserResponse>>

    @GET(USERS_ROUTE)
    suspend fun getUsers(): Response<ApiResponse<UsersListResponse>>
}