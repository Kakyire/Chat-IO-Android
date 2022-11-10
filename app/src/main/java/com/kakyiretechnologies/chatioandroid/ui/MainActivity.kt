package com.kakyiretechnologies.chatioandroid.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.kakyiretechnologies.chatioandroid.R
import com.kakyiretechnologies.chatioandroid.data.api.socketio.SocketIOUtils
import com.kakyiretechnologies.chatioandroid.data.model.payload.OnlineUserPayload
import com.kakyiretechnologies.chatioandroid.databinding.ActivityMainBinding
import com.kakyiretechnologies.chatioandroid.preferences.Keys.IS_USER_LOGGED_IN
import com.kakyiretechnologies.chatioandroid.preferences.Keys.USER_ID
import com.kakyiretechnologies.chatioandroid.preferences.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    @Inject
    lateinit var preferenceManager: PreferenceManager

    private val isUserLoggedIn by lazy { preferenceManager.getBoolean(IS_USER_LOGGED_IN) }

    @Inject
    lateinit var socketIOUtils: SocketIOUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        Timber.tag(TAG).d("logged in: $isUserLoggedIn")

        setupNavigationUI()

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun setupNavigationUI() = with(binding) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navContainer) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.chatFragment, R.id.loginFragment, R.id.createAccountFragment
            ),
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        hideActionBar()

    }

    private fun hideActionBar() {
        navController.addOnDestinationChangedListener { _, destionation, _ ->
            if ((destionation.id == R.id.loginFragment || destionation.id == R.id.createAccountFragment)) {
                supportActionBar?.hide()
            } else {
                supportActionBar?.show()
            }
        }
    }

    override fun onStart() {
        super.onStart()

        Timber.tag("TAG").d("onStart")


    }

    override fun onPause() {
        super.onPause()
        socketIOUtils.disconnect()
    }

    override fun onResume() {
        super.onResume()
        if (isUserLoggedIn) {
            val userId = preferenceManager.getString(USER_ID)
            val onlineUserPayload = OnlineUserPayload(userId!!)
            socketIOUtils.apply {
                connect()
                joinOnlineUsers(onlineUserPayload)
            }
        }



    }

    override fun onDestroy() {
        super.onDestroy()
        socketIOUtils.disconnect()
    }
}