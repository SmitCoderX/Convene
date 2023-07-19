package com.smitcoderx.convene.Ui.Profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smitcoderx.convene.Ui.Login.Models.LoginData
import com.smitcoderx.convene.Utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {

    val isNetworkConnectedLiveData = MutableLiveData<Boolean>()
    private val _profileDataLiveData = MutableLiveData<Resource<String?>>()
    val profileDataLiveData: LiveData<Resource<String?>>
        get() = _profileDataLiveData

    private val _fetchProfileDataLiveData = MutableLiveData<Resource<LoginData?>>()
    val fetchProfileDataLiveData: LiveData<Resource<LoginData?>>
        get() = _fetchProfileDataLiveData

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

    fun fetchUserDetails(id: String) =
        viewModelScope.launch {
            _fetchProfileDataLiveData.value = Resource.Loading()
            if (isNetworkConnectedLiveData.value == true) {
                _fetchProfileDataLiveData.value = Resource.Loading()
                val result = repository.fetchData(id)
                _fetchProfileDataLiveData.value = result
            } else {
                _fetchProfileDataLiveData.value = Resource.Error("No Internet Connection")
            }
        }

}