package com.kakyiretechnologies.chatioandroid.data.repositories.user


import com.kakyiretechnologies.chatioandroid.data.api.ApiService
import com.kakyiretechnologies.chatioandroid.data.model.response.ApiResponse
import com.kakyiretechnologies.chatioandroid.data.model.response.UserResponse
import com.kakyiretechnologies.chatioandroid.data.model.response.UsersListResponse
import com.kakyiretechnologies.chatioandroid.utils.DataState
import com.kakyiretechnologies.chatioandroid.utils.makeNetworkCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 27, 2022.
 * https://github.com/kakyire
 */
class UserRepositoryImpl @Inject constructor(private val apiService: ApiService) : UserRepository {
    override fun getUserProfile(): Flow<DataState<ApiResponse<UserResponse>>> {
        return makeNetworkCall { apiService.getUserProfile() }
    }

    override fun getUsers(): Flow<DataState<ApiResponse<UsersListResponse>>> {
        return makeNetworkCall { apiService.getUsers() }
    }

    override fun getOtherUserProfile(id: String): Flow<DataState<ApiResponse<UserResponse>>> {
        return makeNetworkCall { apiService.getOtherUserProfile(id) }
    }
}