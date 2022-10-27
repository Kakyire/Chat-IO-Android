package com.example.chatioandroid.utils


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 23, 2022.
 * https://github.com/kakyire
 */

const val BASE_URL="http://10.0.2.2:3000/"
private const val API="api/v1/"


//routes
const val SIGNUP_ROUTE ="${API}auth/signup"
const val LOGIN_ROUTE ="${API}auth/login"
const val USERS_ROUTE ="${API}user/getUsers"
const val MY_PROFILE_ROUTE ="${API}user/my-profile"
const val OTHERS_PROFILE_ROUTE ="${API}user/others-profile/{id}"




const val OK_HTTP_TIMEOUT = 30L
