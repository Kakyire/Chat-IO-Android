package com.kakyiretechnologies.chatioandroid.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kakyiretechnologies.chatioandroid.data.model.request.LoginRequest
import com.kakyiretechnologies.chatioandroid.data.model.request.SignUpRequest
import com.kakyiretechnologies.chatioandroid.data.model.response.ApiResponse
import com.kakyiretechnologies.chatioandroid.data.model.response.UserResponse
import com.kakyiretechnologies.chatioandroid.data.repositories.auth.AuthRepository
import com.kakyiretechnologies.chatioandroid.extensions.emitFlowResults
import com.kakyiretechnologies.chatioandroid.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 23, 2022.
 * https://github.com/kakyire
 */
@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {


    private val _login = MutableLiveData<DataState<ApiResponse<UserResponse>>>()
    val login: LiveData<DataState<ApiResponse<UserResponse>>> = _login


    private val _signUp = MutableLiveData<DataState<ApiResponse<UserResponse>>>()
    val signUp: LiveData<DataState<ApiResponse<UserResponse>>> = _signUp


    fun createAccount(signUpRequest: SignUpRequest) = emitFlowResults(_signUp) {
        authRepository.signUp(signUpRequest)
    }

    fun signIn(loginRequest: LoginRequest)=emitFlowResults(_login){
        authRepository.login(loginRequest)
    }
}