package com.smitcoderx.convene.Ui.Login

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor() {
    private lateinit var auth: FirebaseAuth

    suspend fun signInWithEmail(email: String, password: String): FirebaseUser? {
        auth = Firebase.auth
        var user: FirebaseUser? = null
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                user = if(it.isSuccessful) {
                    auth.currentUser
                } else {
                    null
                }
            }
        return user
    }

}