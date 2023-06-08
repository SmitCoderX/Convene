package com.smitcoderx.convene.Ui.Profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.smitcoderx.convene.Ui.Login.Models.LoginData
import com.smitcoderx.convene.Ui.Profile.Model.ProfileDataModel
import com.smitcoderx.convene.Utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
): ViewModel() {

    val isNetworkConnectedLiveData = MutableLiveData<Boolean>()
    private val _profileDataLiveData = MutableLiveData<Resource<String?>>()
    val profileDataLiveData: LiveData<Resource<String?>>
        get() = _profileDataLiveData

    fun updateUserDetails(id: String, profileDataModel: LoginData) =
        viewModelScope.launch {
            _profileDataLiveData.value = Resource.Loading()
            if (isNetworkConnectedLiveData.value == true) {
                _profileDataLiveData.value = Resource.Loading()
                val result = repository.updateUserData(id, profileDataModel)
                _profileDataLiveData.value = result
            } else {
                _profileDataLiveData.value = Resource.Error("No Internet Connection")
            }
        }


}