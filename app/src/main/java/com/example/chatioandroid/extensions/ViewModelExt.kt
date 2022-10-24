package com.example.chatioandroid.extensions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatioandroid.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 23, 2022.
 * https://github.com/kakyire
 */

fun <T> ViewModel.emitFlowResults(
    liveData: MutableLiveData<DataState<T>>,
    request: () -> Flow<T>
) {
    viewModelScope.launch(Dispatchers.IO) {
        request()
            .onStart { liveData.postValue(DataState.Loading) }
            .onEach {
                liveData.postValue(DataState.Success(it))
            }
            .catch { liveData.postValue(DataState.Error(it.localizedMessage!!)) }
            .launchIn(this)
    }
}

