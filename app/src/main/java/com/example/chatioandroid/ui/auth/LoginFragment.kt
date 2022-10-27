package com.example.chatioandroid.ui.auth

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.chatioandroid.R
import com.example.chatioandroid.data.model.request.LoginRequest
import com.example.chatioandroid.databinding.FragmentLoginBinding
import com.example.chatioandroid.extensions.navigateToNextPage
import com.example.chatioandroid.extensions.observeLiveData
import com.example.chatioandroid.preferences.Keys.ACCESS_TOKEN
import com.example.chatioandroid.preferences.Keys.IS_USER_LOGGED_IN
import com.example.chatioandroid.preferences.PreferenceManager
import com.example.chatioandroid.ui.viewModels.MainViewModel
import com.example.chatioandroid.utils.EmailValidation.isEmailValid
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 24, 2022.
 * https://github.com/kakyire
 */


@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding

    private val mainViewModel by viewModels<MainViewModel>()

    @Inject
    lateinit var preferenceManager: PreferenceManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        clickListeners()
        onTextChangeListeners()
        observeViewModelCallbacks()
    }


    override fun onStart() {
        super.onStart()
        val isLoggedIn = preferenceManager.getBoolean(IS_USER_LOGGED_IN)
        if (isLoggedIn) navigateToNextPage(
            LoginFragmentDirections
                .actionLoginFragmentToChatFragment()
        )
    }

    private fun clickListeners() = with(binding) {

        btnCreateAccount.setOnClickListener {
            navigateToNextPage(
                LoginFragmentDirections
                    .actionLoginFragmentToCreateAccountFragment()
            )
        }

        btnLogin.setOnClickListener {
            login()

        }
    }

    private fun observeViewModelCallbacks() = with(mainViewModel) {
        observeLiveData(login, progressBar = binding.progressBar) {

            preferenceManager.apply {
                save(IS_USER_LOGGED_IN, true)
                save(ACCESS_TOKEN, it.token)
            }

            navigateToNextPage(LoginFragmentDirections.actionLoginFragmentToChatFragment())

        }
    }

    private fun login() = with(binding) {
        val email = edtEmail.text.toString()
        val password = edtPassword.text.toString()
        val loginRequest = LoginRequest(email, password)

        if (areFieldsFilled(loginRequest)) mainViewModel.signIn(loginRequest)

    }

    private fun FragmentLoginBinding.areFieldsFilled(loginRequest: LoginRequest): Boolean {
        return when {
            loginRequest.email.isEmpty() -> {
                emailLayout.error = getString(R.string.emal_is_required)
                false
            }
            loginRequest.password.isEmpty() -> {
                passwordLayout.error = getString(R.string.password_is_required)
                false
            }
            !isEmailValid(loginRequest.email) -> {
                emailLayout.error = getString(R.string.enter_valid_email)
                false
            }
            else -> true
        }
    }

    private fun onTextChangeListeners() = with(binding) {
        edtEmail.doAfterTextChanged { emailLayout.error = "" }
        edtPassword.doAfterTextChanged { passwordLayout.error = "" }

    }


}