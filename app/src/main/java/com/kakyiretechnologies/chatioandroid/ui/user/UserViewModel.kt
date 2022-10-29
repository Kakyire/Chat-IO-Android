package com.kakyiretechnologies.chatioandroid.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kakyiretechnologies.chatioandroid.data.model.response.ApiResponse
import com.kakyiretechnologies.chatioandroid.data.model.response.UserResponse
import com.kakyiretechnologies.chatioandroid.data.model.response.UsersListResponse
import com.kakyiretechnologies.chatioandroid.data.repositories.user.UserRepository
import com.kakyiretechnologies.chatioandroid.extensions.emitFlowResults
import com.kakyiretechnologies.chatioandroid.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 27, 2022.
 * https://github.com/kakyire
 */
@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val _userProfile = MutableLiveData<DataState<ApiResponse<UserResponse>>>()
    val userProfile: LiveData<DataState<ApiResponse<UserResponse>>> = _userProfile


    private val _otherProfile = MutableLiveData<DataState<ApiResponse<UserResponse>>>()
    val otherProfile: LiveData<DataState<ApiResponse<UserResponse>>> = _otherProfile

    private val _usersList = MutableLiveData<DataState<ApiResponse<UsersListResponse>>>()
    val usersList: LiveData<DataState<ApiResponse<UsersListResponse>>> = _usersList

    fun getUserProfile() = emitFlowResults(_userProfile) { userRepository.getUserProfile() }

    fun getOtherUserProfile(id: String) =
        emitFlowResults(_otherProfile) { userRepository.getOtherUserProfile(id) }

    fun getUsers() = emitFlowResults(_usersList) { userRepository.getUsers() }
}