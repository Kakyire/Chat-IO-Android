package com.example.chatioandroid.ui.chat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.chatioandroid.R
import com.example.chatioandroid.databinding.FragmentChatBinding
import dagger.hilt.android.AndroidEntryPoint


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 24, 2022.
 * https://github.com/kakyire
 */


 @AndroidEntryPoint
 class ChatFragment: Fragment(R.layout.fragment_chat) {

     private lateinit var binding: FragmentChatBinding

     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)
         binding= FragmentChatBinding.bind(view)
     }
 }