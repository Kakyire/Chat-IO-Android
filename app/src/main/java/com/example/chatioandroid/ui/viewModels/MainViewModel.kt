package com.example.chatioandroid.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatioandroid.data.model.request.LoginRequest
import com.example.chatioandroid.data.model.request.SignUpRequest
import com.example.chatioandroid.data.model.response.ApiResponse
import com.example.chatioandroid.data.model.response.AuthResponse
import com.example.chatioandroid.data.model.response.UserResponse
import com.example.chatioandroid.data.model.response.UsersListResponse
import com.example.chatioandroid.data.repositories.auth.AuthRepository
import com.example.chatioandroid.extensions.emitFlowResults
import com.example.chatioandroid.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 23, 2022.
 * https://github.com/kakyire
 */
@HiltViewModel
class MainViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {


    private val _login = MutableLiveData<DataState<ApiResponse<AuthResponse>>>()
    val login: LiveData<DataState<ApiResponse<AuthResponse>>> = _login


    private val _signUp = MutableLiveData<DataState<ApiResponse<AuthResponse>>>()
    val signUp: LiveData<DataState<ApiResponse<AuthResponse>>> = _signUp


    fun createAccount(signUpRequest: SignUpRequest) = emitFlowResults(_signUp) {
        authRepository.signUp(signUpRequest)
    }

    fun signIn(loginRequest: LoginRequest)=emitFlowResults(_login){
        authRepository.login(loginRequest)
    }
}