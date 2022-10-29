package com.kakyiretechnologies.chatioandroid.data.model.response


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 23, 2022.
 * https://github.com/kakyire
 */
data class ApiResponse<T>(val data:T,val success:Boolean,val message:String)