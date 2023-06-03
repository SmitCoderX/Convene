package com.smitcoderx.convene.Ui.Login

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.smitcoderx.convene.Utils.Resource
import com.smitcoderx.convene.Utils.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor(private val auth: FirebaseAuth) {
    suspend fun loginAccount(email: String, password: String): Resource<FirebaseUser?> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            Resource.Success(result.user)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message.toString())
        }
    }

    fun signOut() {
        auth.signOut()
    }
}