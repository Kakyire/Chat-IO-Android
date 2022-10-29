package com.kakyiretechnologies.chatioandroid.extensions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakyiretechnologies.chatioandroid.utils.DataState
import com.kakyiretechnologies.chatioandroid.utils.livedata.Event
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
    request: () -> Flow<DataState<T>>
) {
    viewModelScope.launch(Dispatchers.IO) {
        request()
            .onStart { liveData.postValue(DataState.Loading) }
            .onEach {
                liveData.postValue(it)
            }
            .catch {
                liveData.postValue(DataState.Error(it.localizedMessage))
            }
            .launchIn(this)
    }
}

fun <T> ViewModel.emitEventFlowResults(
    liveData: MutableLiveData<Event<DataState<T>>>,
    request: () -> Flow<DataState<T>>
) {
    viewModelScope.launch(Dispatchers.IO) {
        request()
            .onStart { liveData.postValue(Event(DataState.Loading)) }
            .onEach {
                liveData.postValue(Event(it))
            }
            .catch {
                liveData.postValue(Event(DataState.Error(it.localizedMessage)))
            }
            .launchIn(this)
    }
}

