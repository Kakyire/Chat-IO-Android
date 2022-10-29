package com.kakyiretechnologies.chatioandroid.data.api

import com.kakyiretechnologies.chatioandroid.data.model.request.LoginRequest
import com.kakyiretechnologies.chatioandroid.data.model.request.MessageModelRequest
import com.kakyiretechnologies.chatioandroid.data.model.request.SignUpRequest
import com.kakyiretechnologies.chatioandroid.data.model.response.*
import com.kakyiretechnologies.chatioandroid.utils.*
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
    suspend fun login(@Body loginRequest: LoginRequest): Response<ApiResponse<UserResponse>>

    @POST(SIGNUP_ROUTE)
    suspend fun signUp(@Body signUpRequest: SignUpRequest): Response<ApiResponse<UserResponse>>

    @GET(MY_PROFILE_ROUTE)
    suspend fun getUserProfile(): Response<ApiResponse<UserResponse>>

    @GET(OTHERS_PROFILE_ROUTE)
    suspend fun getOtherUserProfile(@Path("id") id: String): Response<ApiResponse<UserResponse>>

    @GET(USERS_ROUTE)
    suspend fun getUsers(): Response<ApiResponse<UsersListResponse>>

    @POST(SEND_MESSAGE_ROUTE)
    suspend fun sendMessage(@Body messageModelRequest: MessageModelRequest): Response<ApiResponse<MessageResponse>>

    @GET(MESSAGES_ROUTE)
    suspend fun getMessages(@Path("chatId") chatId: String): Response<ApiResponse<MessagesListResponse>>

    @GET(CHATS_ROUTE)
    suspend fun getChats(): Response<ApiResponse<ChatListResponse>>


}