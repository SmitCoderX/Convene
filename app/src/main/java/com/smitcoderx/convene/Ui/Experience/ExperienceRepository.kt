package com.smitcoderx.convene.Ui.Experience

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.smitcoderx.convene.Model.ExperienceDataModel
import com.smitcoderx.convene.Utils.Constants.EXP
import com.smitcoderx.convene.Utils.Resource
import com.smitcoderx.convene.Utils.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExperienceRepository @Inject constructor(
    private val auth: FirebaseAuth
) {

    private val userCollectionRef = Firebase.firestore.collection("users")

    suspend fun fetchJobs(id: String): Resource<ArrayList<ExperienceDataModel?>> {
        val fetchedJobs = ArrayList<ExperienceDataModel?>()
        return try {
            val documentSnapshot = userCollectionRef.document(id).collection(EXP).get().await()
            documentSnapshot.documents.forEach {
                fetchedJobs.add(it.toObject(ExperienceDataModel::class.java))
            }
            Resource.Success(fetchedJobs)
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

    suspend fun updateExperience(
        id: String, experienceDataModel: ExperienceDataModel
    ): Resource<String> {
        return try {
            userCollectionRef.document(id).collection(EXP).document()
                .set(experienceDataModel, SetOptions.merge()).await()
            Resource.Success("Data Saved Successfully")
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

}