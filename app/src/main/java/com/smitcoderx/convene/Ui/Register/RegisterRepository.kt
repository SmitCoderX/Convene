package com.smitcoderx.convene.Ui.Register

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.smitcoderx.convene.Utils.Constants
import com.smitcoderx.convene.Utils.Resource
import com.smitcoderx.convene.Utils.await
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random


@Singleton
class RegisterRepository @Inject constructor(private val auth: FirebaseAuth) {
    suspend fun createAccount(username: String, email: String, password: String): Resource<FirebaseUser?> {
        val uri = Uri.parse("${Constants.IMAGE_URL}${username}.png?apikey=${Constants.MULTI_VAR}")
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            result?.user?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(username).setPhotoUri(uri).build())?.await()
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