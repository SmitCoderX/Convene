package com.smitcoderx.convene.Ui.Experience

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.smitcoderx.convene.Model.ExperienceDataModel
import com.smitcoderx.convene.Utils.Resource
import com.smitcoderx.convene.Utils.await
import java.util.Arrays
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExperienceRepository @Inject constructor(
    private val auth: FirebaseAuth
) {

    private val userCollectionRef = Firebase.firestore.collection("users")
    val data = arrayListOf<ExperienceDataModel>()
    suspend fun updateExperience(id: String, experienceDataModel: ExperienceDataModel): Resource<String> {
        return try {
            data.add(experienceDataModel)
            userCollectionRef.document(id).update("profileData.experience", data).await()
            Resource.Success("Data Saved Successfully")
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

}