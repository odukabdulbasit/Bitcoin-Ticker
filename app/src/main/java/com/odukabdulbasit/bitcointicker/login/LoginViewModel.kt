package com.odukabdulbasit.bitcointicker.login

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel(val application: Application) : ViewModel() {

    private val firebaseAuth : FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val _goToRegister = MutableLiveData<Boolean>()
    val goToRegister: LiveData<Boolean>
        get() = _goToRegister

    init {
        _goToRegister.value = false
    }
    //login to firebase current user with email and password
    fun login(email: String , password: String){
        if (!email.isNullOrEmpty() && !password.isNullOrEmpty()){
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(application, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }
        else {
            Toast.makeText(application, "Lütfen gerekli alanları doldurunuz", Toast.LENGTH_LONG).show()
        }

    }

    fun goToRegister(){

        _goToRegister.value = true
    }

    fun onNavigatedToRegisterCompleted(){

        _goToRegister.value = false
    }


    /**
     * Factory for constructing DevByteViewModel with parameter
     */
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return LoginViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}



