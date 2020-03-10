package com.minhbka.firebasemvvm.utils

import android.content.Context
import android.content.Intent
import com.minhbka.firebasemvvm.ui.auth.LoginActivity
import com.minhbka.firebasemvvm.ui.home.HomeActivity

fun Context.startHomeActivity(){
    Intent(this, HomeActivity::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}

fun Context.startLoginActivity() =
    Intent(this, LoginActivity::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }