package com.example.chatioandroid.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.example.chatioandroid.R
import com.example.chatioandroid.databinding.ActivityMainBinding
import com.example.chatioandroid.utils.BASE_URL
import dagger.hilt.android.AndroidEntryPoint
import io.socket.client.IO
import io.socket.client.Socket
import kotlinx.coroutines.launch
import timber.log.Timber

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

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

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navContainer) as NavHostFragment
        navHostFragment.navController

        binding.apply {
            edtMessage.doAfterTextChanged {
                btnSend.isEnabled = !it.isNullOrEmpty()
            }
            btnSend.setOnClickListener {
                val message = edtMessage.text.toString()
                sentMessages = "$sentMessages \n $message"
                if (message.isEmpty()) {
                    Toast.makeText(
                        this@MainActivity, "You can't send empty message", Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                socketIO.emit("message", message)
                tvSent.text = sentMessages
                edtMessage.setText("")

            }
        }

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

    override fun onStart() {
        super.onStart()
        socketIO.connect()
    }

    override fun onPause() {
        super.onPause()
        socketIO.disconnect()
    }
}