package com.example.chatioandroid.ui.chat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.chatioandroid.R
import com.example.chatioandroid.databinding.FragmentChatBinding
import com.example.chatioandroid.extensions.createMenu
import com.example.chatioandroid.extensions.navigateToNextPage
import com.example.chatioandroid.extensions.observeLiveData
import com.example.chatioandroid.preferences.Keys.IS_USER_LOGGED_IN
import com.example.chatioandroid.preferences.PreferenceManager
import com.example.chatioandroid.ui.chat.adapters.ChatListAdapter
import com.example.chatioandroid.ui.viewModels.UserViewModel
import com.example.chatioandroid.utils.OnItemClickListener
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 24, 2022.
 * https://github.com/kakyire
 */

private const val TAG = "ChatFragment"

@AndroidEntryPoint
class ChatFragment : Fragment(R.layout.fragment_chat), OnItemClickListener {

    private lateinit var binding: FragmentChatBinding

    private val userViewModel by viewModels<UserViewModel>()

    @Inject
    lateinit var preferenceManager: PreferenceManager

    @Inject
    lateinit var chatListAdapter: ChatListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChatBinding.bind(view)

        createOptionMenu()
        clickListeners()
        setupRecyclerView()
        observeViewModelCallbacks()
    }

    private fun observeViewModelCallbacks() = with(userViewModel) {
        getUsersList()
        observeLiveData(usersList) {
            chatListAdapter.submitList(it.users)
         
          Timber.tag(TAG).d("Users ${Gson().toJson(it)}")
          }
    }

    private fun setupRecyclerView() = with(binding) {
        rvUsers.adapter = chatListAdapter
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
    }

    override fun onItemClick(model: Any) {

        Timber.tag(TAG).d("$model")

    }


}