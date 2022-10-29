package com.kakyiretechnologies.chatioandroid.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.kakyiretechnologies.chatioandroid.R
import com.kakyiretechnologies.chatioandroid.databinding.ActivityMainBinding
import com.kakyiretechnologies.chatioandroid.utils.BASE_URL
import dagger.hilt.android.AndroidEntryPoint
import io.socket.client.IO
import io.socket.client.Socket
import kotlinx.coroutines.launch
import timber.log.Timber

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    //    private val hostUrl = "http://localhost:3000/"
//    private val hostUrl = "http://192.168.154.9:3000/"
    private val hostUrl = BASE_URL//"http://10.0.2.2:3000"

    private lateinit var socketIO: Socket
    private var sentMessages = ""
    private var receivedMessages = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        Timber.tag(TAG).d("Home")

        setupNavigationUI()



        socketIO = IO.socket(hostUrl)
        socketIO.apply {
            on("connect") {
                Timber.tag(TAG).d("connected with " + id())
            }

            on("message") {
                Timber.tag(TAG).d("New Message: " + it[0])
                receivedMessages = "$receivedMessages \n ${it[0]}"
                lifecycleScope.launch {
                    binding.tvReceived.text = receivedMessages


                }
                on("disconnect") {
                    Timber.tag(TAG).d("Disconnected from server")
                }
            }


        }


    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)|| super.onSupportNavigateUp()
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
        setupActionBarWithNavController(navController,appBarConfiguration)
        hideActionBar()

    }

    private  fun hideActionBar (){
        navController.addOnDestinationChangedListener{_,destionation,_->
           if ((destionation.id==R.id.loginFragment||destionation.id==R.id.createAccountFragment))
           {
               supportActionBar?.hide()
           }else {
               supportActionBar?.show()
           }
        }
    }
    override fun onStart() {
        super.onStart()
        socketIO.connect()
    }

    override fun onPause() {
        super.onPause()
        socketIO.disconnect()
    }
}