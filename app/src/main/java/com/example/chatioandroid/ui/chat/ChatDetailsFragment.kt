package com.example.chatioandroid.ui.chat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.chatioandroid.R
import com.example.chatioandroid.databinding.FragmentChatDetailsBinding
import com.example.chatioandroid.extensions.observeLiveData
import com.example.chatioandroid.ui.viewModels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 24, 2022.
 * https://github.com/kakyire
 */
private const val TAG = "ChatDetails"

@AndroidEntryPoint
class ChatDetailsFragment : Fragment(R.layout.fragment_chat_details) {

    private lateinit var binding: FragmentChatDetailsBinding

    private val args by navArgs<ChatDetailsFragmentArgs>()

    private val userViewModel by viewModels<UserViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChatDetailsBinding.bind(view)
        observeViewModelCallbacks()
         

    }

    private fun observeViewModelCallbacks() = with(userViewModel) {
        getOtherUserProfile(args.id)
        observeLiveData(otherProfile) {
            binding.tvUsername.text = it.username

        }
    }
}