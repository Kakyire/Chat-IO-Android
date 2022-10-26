package com.example.chatioandroid.ui.auth

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chatioandroid.R
import com.example.chatioandroid.data.model.request.SignUpRequest
import com.example.chatioandroid.databinding.FragmentCreateAccountBinding
import com.example.chatioandroid.extensions.navigateToNextPage
import com.example.chatioandroid.extensions.observeLiveData
import com.example.chatioandroid.preferences.Keys.ACCESS_TOKEN
import com.example.chatioandroid.preferences.Keys.IS_USER_LOGGED_IN
import com.example.chatioandroid.preferences.PreferenceManager
import com.example.chatioandroid.ui.MainViewModel
import com.example.chatioandroid.utils.EmailValidation.isEmailValid
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 24, 2022.
 * https://github.com/kakyire
 */

@AndroidEntryPoint
class CreateAccountFragment : Fragment(R.layout.fragment_create_account) {

    private lateinit var binding: FragmentCreateAccountBinding
    private val mainViewModel by viewModels<MainViewModel>()

    @Inject
    lateinit var preferenceManager: PreferenceManager

    private var username = ""
    private var email = ""
    private var password = ""
    private var confirmPassword = ""
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCreateAccountBinding.bind(view)
        clickListeners()
        onTextChangeListeners()
        observeViewModelCallbacks()

    }

    private fun observeViewModelCallbacks() = with(mainViewModel) {
        observeLiveData(signUp, progressBar = binding.progressBar) {


            preferenceManager.apply {
                save(IS_USER_LOGGED_IN, true)
                save(ACCESS_TOKEN, it.token)
            }
            navigateToNextPage(
                CreateAccountFragmentDirections
                    .actionCreateAccountFragmentToChatFragment()
            )
        }
    }

    private fun clickListeners() = with(binding) {

        btnHaveAccount.setOnClickListener {
            navigateToNextPage(
                CreateAccountFragmentDirections.actionCreateAccountFragmentToLoginFragment()
            )
        }

        btnSignUp.setOnClickListener {
            createAccount()
        }
    }

    private fun getFieldsValues() = with(binding) {
        username = edtUsername.text.toString()
        email = edtEmail.text.toString()
        password = edtPassword.text.toString()
        confirmPassword = edtConfirmPassword.text.toString()
    }

    private fun createAccount() = with(binding) {

        getFieldsValues()
        val signUpRequest = SignUpRequest(
            username = username,
            password = password,
            email = email
        )
        if (areFieldsFilled())
            mainViewModel.createAccount(signUpRequest)

    }


    private fun onTextChangeListeners() = with(binding) {
        edtEmail.doAfterTextChanged { emailLayout.error = "" }
        edtPassword.doAfterTextChanged { passwordLayout.error = "" }
        edtConfirmPassword.doAfterTextChanged { confirmPasswordLayout.error = "" }

    }

    private fun FragmentCreateAccountBinding.areFieldsFilled(): Boolean {
        getFieldsValues()

        return when {
            email.isEmpty() -> {
                emailLayout.error = getString(R.string.emal_is_required)
                false
            }
            !isEmailValid(email) -> {
                emailLayout.error = getString(R.string.enter_valid_email)
                false
            }
            password.isEmpty() -> {
                passwordLayout.error = getString(R.string.password_is_required)
                false
            }
            password != confirmPassword -> {
                confirmPasswordLayout.error = "Password don't match"
                false
            }

            else -> {
                true
            }
        }


    }
}