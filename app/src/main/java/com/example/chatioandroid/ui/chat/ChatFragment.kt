package com.example.chatioandroid.ui.chat

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.example.chatioandroid.R
import com.example.chatioandroid.databinding.FragmentChatBinding
import com.example.chatioandroid.extensions.createMenu
import com.example.chatioandroid.extensions.navigateToNextPage
import com.example.chatioandroid.preferences.Keys.IS_USER_LOGGED_IN
import com.example.chatioandroid.preferences.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 24, 2022.
 * https://github.com/kakyire
 */


@AndroidEntryPoint
class ChatFragment : Fragment(R.layout.fragment_chat) {

    private lateinit var binding: FragmentChatBinding

    @Inject
    lateinit var preferenceManager: PreferenceManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChatBinding.bind(view)

        createOptionMenu()
        clickListeners()
    }

    private fun createOptionMenu() {
        createMenu(R.menu.chat_menu) {
            if (it == R.id.menLogout) {
                preferenceManager.save(IS_USER_LOGGED_IN, false)
                navigateToNextPage(
                    ChatFragmentDirections
                        .actionChatFragmentToLoginFragment()
                )
            }
        }
    }

    private fun clickListeners() = with(binding) {
        btnChat.setOnClickListener {
            navigateToNextPage(
                ChatFragmentDirections
                    .actionChatFragmentToChatDetailsFragment()
            )
        }
    }


}