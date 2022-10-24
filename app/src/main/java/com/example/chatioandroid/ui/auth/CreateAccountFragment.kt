package com.example.chatioandroid.ui.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.chatioandroid.R
import com.example.chatioandroid.databinding.FragmentCreateAccountBinding
import dagger.hilt.android.AndroidEntryPoint


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 24, 2022.
 * https://github.com/kakyire
 */

 @AndroidEntryPoint
 class CreateAccountFragment: Fragment(R.layout.fragment_create_account) {
     
     private lateinit var binding:FragmentCreateAccountBinding
 
     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)
         binding= FragmentCreateAccountBinding.bind(view)
     }
 }