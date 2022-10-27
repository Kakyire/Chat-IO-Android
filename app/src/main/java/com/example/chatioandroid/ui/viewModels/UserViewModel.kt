package com.example.chatioandroid.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatioandroid.data.model.response.ApiResponse
import com.example.chatioandroid.data.model.response.UserResponse
import com.example.chatioandroid.data.model.response.UsersListResponse
import com.example.chatioandroid.data.repositories.user.UserRepository
import com.example.chatioandroid.extensions.emitFlowResults
import com.example.chatioandroid.utils.DataState
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

    private val _usersList = MutableLiveData<DataState<ApiResponse<UsersListResponse>>>()
    val usersList: LiveData<DataState<ApiResponse<UsersListResponse>>> = _usersList

    fun getUserProfile() = emitFlowResults(_userProfile) { userRepository.getUserProfile() }

    fun getUsersList() = emitFlowResults(_usersList) { userRepository.getUsers() }
}