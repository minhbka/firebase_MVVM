package com.minhbka.firebasemvvm.data.firebase

import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Completable
// this class is created to handle Firebase operations some how similar as API
class FirebaseSource {
    private val firebaseAuth : FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    fun login(email : String, password:String) = Completable.create {emitter->
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (!emitter.isDisposed){
                if(it.isSuccessful)
                    emitter.onComplete()
                else
                    emitter.onError(it.exception!!)
            }
        }
    }

    fun register(email:String, password: String) = Completable.create { emitter ->
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (!emitter.isDisposed){
                emitter.onComplete()
            }
            else{
                emitter.onError(it.exception!!)
            }
        }
    }

    fun logout() = firebaseAuth.signOut()

    fun currentUser() = firebaseAuth.currentUser
}