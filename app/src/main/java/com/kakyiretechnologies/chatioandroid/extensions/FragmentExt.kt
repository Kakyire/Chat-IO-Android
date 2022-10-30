package com.kakyiretechnologies.chatioandroid.extensions

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.MenuRes
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.kakyiretechnologies.chatioandroid.data.model.response.ApiResponse
import com.kakyiretechnologies.chatioandroid.data.model.response.UserResponse
import com.kakyiretechnologies.chatioandroid.preferences.Keys.ACCESS_TOKEN
import com.kakyiretechnologies.chatioandroid.preferences.Keys.IS_USER_LOGGED_IN
import com.kakyiretechnologies.chatioandroid.preferences.Keys.USER_ID
import com.kakyiretechnologies.chatioandroid.preferences.PreferenceManager
import com.kakyiretechnologies.chatioandroid.utils.DataState
import com.kakyiretechnologies.chatioandroid.utils.livedata.Event
import com.kakyiretechnologies.chatioandroid.utils.livedata.EventObserver
import timber.log.Timber


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 24, 2022.
 * https://github.com/kakyire
 */


fun Fragment.navigateToNextPage(direction: NavDirections) {
    findNavController().navigate(direction)
}

fun Fragment.toast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun Fragment.createMenu(@MenuRes menuRes: Int, onItemSelected: (Int) -> Unit) {
    val menuHost: MenuHost = requireActivity()

    menuHost.addMenuProvider(object : MenuProvider {
        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            menuInflater.inflate(menuRes, menu)
        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
            onItemSelected(menuItem.itemId)
            return true
        }
    }, viewLifecycleOwner, Lifecycle.State.RESUMED)
}

fun <T> Fragment.observeLiveData(
    liveData: LiveData<DataState<ApiResponse<T>>>,
    showProgress: Boolean = true,
    progressBar: ProgressBar? = null,
    onSuccess: (T) -> Unit
) {
    liveData.observe(viewLifecycleOwner) { response ->
        when (response) {
            is DataState.Error -> {
                progressBar?.isVisible = false
                toast(response.message)

                  Timber.tag(TAG).d(response.message)

            }
            is DataState.Success -> {
                progressBar?.isVisible = false
                onSuccess(response.data.data)
            }

            is DataState.Loading -> {
                progressBar?.isVisible = showProgress
            }

        }

    }
}

fun <T> Fragment.observeLiveDataEvent(
    liveData: LiveData<Event<DataState<ApiResponse<T>>>>,
    showProgress: Boolean = true,
    progressBar: ProgressBar? = null,
    onSuccess: (T) -> Unit
) {
    liveData.observe(viewLifecycleOwner,EventObserver{ response ->
        when (response) {
            is DataState.Error -> {
                progressBar?.isVisible = false
                toast(response.message)

                Timber.tag(TAG).d(response.message)

            }
            is DataState.Success -> {
                progressBar?.isVisible = false
                onSuccess(response.data.data)
            }

            is DataState.Loading -> {
                progressBar?.isVisible = showProgress
            }

        }

    })
}

fun saveUserPrefs(preferenceManager: PreferenceManager, user: UserResponse){
    preferenceManager.apply {
        save(IS_USER_LOGGED_IN, true)
        save(ACCESS_TOKEN, user.token)
        save(USER_ID,user.id)
    }
}

private const val TAG = "FragmentExt"

