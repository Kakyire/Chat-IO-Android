package com.kakyiretechnologies.chatioandroid.ui.user

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kakyiretechnologies.chatioandroid.R
import com.kakyiretechnologies.chatioandroid.data.model.response.UserResponse
import com.kakyiretechnologies.chatioandroid.databinding.FragmentStartChatBinding
import com.kakyiretechnologies.chatioandroid.extensions.navigateToNextPage
import com.kakyiretechnologies.chatioandroid.extensions.observeLiveData
import com.kakyiretechnologies.chatioandroid.ui.user.adapters.UsersListAdapter
import com.kakyiretechnologies.chatioandroid.utils.OnItemClickListener
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 28, 2022.
 * https://github.com/kakyire
 */

@AndroidEntryPoint
class StartChatFragment : Fragment(R.layout.fragment_start_chat), OnItemClickListener {

    private lateinit var binding: FragmentStartChatBinding

    private val userViewModel by viewModels<UserViewModel>()

    @Inject
    lateinit var usersListAdapter: UsersListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStartChatBinding.bind(view)
        observeViewModelCallbacks()
        setupRecyclerView()
    }

    private fun setupRecyclerView() = with(binding) {
        rvUsers.adapter = usersListAdapter
    }

    private fun observeViewModelCallbacks() = with(userViewModel) {
        getUsers()
        observeLiveData(usersList) {
            usersListAdapter.submitList(it.users)

        }
    }

    override fun onItemClick(model: Any) {
        val user = model as UserResponse

        Timber.tag(TAG).d("username:${user.username}")

        navigateToNextPage(
            StartChatFragmentDirections
                .actionStartChatFragmentToChatDetailsFragment(
                    username = user.username, receiverId = user.id
                )
        )
    }
}

private const val TAG = "StartChatFragment"