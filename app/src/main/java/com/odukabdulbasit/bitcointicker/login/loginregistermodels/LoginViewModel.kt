package com.odukabdulbasit.bitcointicker.login.loginregistermodels

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

    private val _goToListSearch = MutableLiveData<Boolean>()
    val goToListSearch: LiveData<Boolean>
        get() = _goToListSearch

    init {
        _goToRegister.value = false
        _goToListSearch.value = false
    }
    //login to firebase current user with email and password
    fun login(email: String , password: String){
        if (!email.isNullOrEmpty() && !password.isNullOrEmpty()){
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")

                        goToListSearch()
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

    //Navigate to ListSearchFragment
    fun goToListSearch(){
        _goToListSearch.value = true
    }

    fun onNavigatedToListSearchCompleted(){

        _goToListSearch.value = false
    }

    fun goToRegister(){

        _goToRegister.value = true
    }

    fun onNavigatedToRegisterCompleted(){

        _goToRegister.value = false
    }

    fun registerAndGoToList(email: String, password: String){

        Log.i("Register", "register tiklandi")
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")

                    goToListSearch()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(application, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
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



