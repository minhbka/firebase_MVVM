package com.minhbka.firebasemvvm.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.minhbka.firebasemvvm.data.repositories.UserRepository
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AuthViewModel(
    private val repository: UserRepository
):ViewModel() {

    // input email, password
    var email : String? = null
    var password : String? = null
    // auth Listener
    var authListener:AuthListener? = null

    // disposable to dispose the completable
    private val disposables = CompositeDisposable()

    val user by lazy {
        repository.currentUser()
    }

    // function to perform login
    fun login(){
        // validating email and password
        if (email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("Invalid Email or password")
            return
        }

        // authentication started
        authListener?.onStarted()

        // calling login from repository to perform the actual authentication
        val disposable = repository.login(email!!, password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                // sending a success callback
                authListener?.onSuccess()
            },{
                // sending a failure callback
                authListener?.onFailure(it.message!!)
            })

        disposables.add(disposable)


    }

    fun signup(){
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Please input all values")
            return
        }

        authListener?.onStarted()

        val disposable = repository.register(email!!, password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authListener?.onSuccess()
            },{
                authListener?.onFailure(it.message!!)
            })

        disposables.add(disposable)
    }

    fun goToSignUp(view: View){
        Intent(view.context, SignupActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun goToLogin(view: View){
        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }
}