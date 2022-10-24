package com.example.chatioandroid.ui.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.chatioandroid.R
import com.example.chatioandroid.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 24, 2022.
 * https://github.com/kakyire
 */


 @AndroidEntryPoint
 class LoginFragment: Fragment(R.layout.fragment_login) {

     private lateinit var binding:FragmentLoginBinding

     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)
         binding= FragmentLoginBinding.bind(view)
     }
 }