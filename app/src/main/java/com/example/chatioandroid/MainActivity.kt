package com.example.chatioandroid

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.example.chatioandroid.databinding.ActivityMainBinding
import io.socket.client.IO
import io.socket.client.Socket
import kotlinx.coroutines.launch

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //    private val hostUrl = "http://localhost:3000/"
//    private val hostUrl = "http://192.168.154.9:3000/"
    private val hostUrl = "http://10.0.2.2:3000"

    private lateinit var socketIO: Socket
    private var sentMessages = ""
    private var receivedMessages = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
                Log.d(TAG, "connected with ${id()}")
            }

            on("message") {
                Log.d(TAG, "New Message: ${it[0]}")
                receivedMessages = "$receivedMessages \n ${it[0]}"
                lifecycleScope.launch {
                    binding.tvReceived.text = receivedMessages


                }
                on("disconnect") {
                    Log.d(TAG, "Disconnected from server")
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