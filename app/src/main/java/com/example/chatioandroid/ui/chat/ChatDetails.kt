package com.example.chatioandroid.ui.chat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.chatioandroid.R
import com.example.chatioandroid.databinding.FragmentChatDetailsBinding
import dagger.hilt.android.AndroidEntryPoint


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 24, 2022.
 * https://github.com/kakyire
 */


 @AndroidEntryPoint
 class ChatDetailsFragment: Fragment(R.layout.fragment_chat_details) {

     private lateinit var binding: FragmentChatDetailsBinding

     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)
         binding= FragmentChatDetailsBinding.bind(view)
     }
 }