package com.minhbka.firebasemvvm.ui.home

import android.view.View
import androidx.lifecycle.ViewModel
import com.minhbka.firebasemvvm.data.repositories.UserRepository
import com.minhbka.firebasemvvm.utils.startLoginActivity

class HomeViewModel (
    private val repository: UserRepository
): ViewModel(){
    val user by lazy {
        repository.currentUser()
    }

    fun logout(view: View){
        repository.logOut()
        view.context.startLoginActivity()
    }
}