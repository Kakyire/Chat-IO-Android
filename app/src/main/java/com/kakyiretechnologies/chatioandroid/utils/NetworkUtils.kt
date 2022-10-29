package com.kakyiretechnologies.chatioandroid.utils

import com.google.gson.Gson
import com.kakyiretechnologies.chatioandroid.data.model.response.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 25, 2022.
 * https://github.com/kakyire
 */

fun <T> makeNetworkCall(request: suspend () -> Response<ApiResponse<T>>): Flow<DataState<ApiResponse<T>>> {
    return flow {
        val response = request.invoke()

        if (response.isSuccessful) {
            response.body()?.let { emit(DataState.Success(it)) }
        } else {
            val errorBody = response.errorBody()?.string()

            val error = Gson().fromJson(errorBody, ApiResponse::class.java)

            emit(DataState.Error(error.message))

        }
    }
}