package com.minhbka.firebasemvvm.ui.auth

interface AuthListener {

    fun onStarted()
    fun onSuccess()
    fun onFailure(message:String)
}