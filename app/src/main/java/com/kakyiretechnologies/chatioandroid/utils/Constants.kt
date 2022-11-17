package com.kakyiretechnologies.chatioandroid.utils


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 23, 2022.
 * https://github.com/kakyire
 */

//const val BASE_URL="http://10.0.2.2:3000/"
const val BASE_URL="https://chat-io-ap.herokuapp.com/"
private const val API_V1="api/v1/"


//routes
const val SIGNUP_ROUTE ="${API_V1}auth/signup"
const val LOGIN_ROUTE ="${API_V1}auth/login"
const val USERS_ROUTE ="${API_V1}user/getUsers"
const val MY_PROFILE_ROUTE ="${API_V1}user/my-profile"
const val OTHERS_PROFILE_ROUTE ="${API_V1}user/others-profile/{id}"
const val MESSAGES_ROUTE ="${API_V1}chat/get-messages/{chatId}"
const val SEND_MESSAGE_ROUTE ="${API_V1}chat/send-message"
const val CHATS_ROUTE ="${API_V1}chat/chats"


//socket io events
const val NEW_CHAT_EVENT="new-chat"
const val CHAT_MESSAGES_EVENT="chat-message"
const val CHATS_EVENT="chats"
const val JOIN_CHAT_EVENT ="join-chat"
const val ONLINE_USER_EVENT="online"


const val OK_HTTP_TIMEOUT = 30L
