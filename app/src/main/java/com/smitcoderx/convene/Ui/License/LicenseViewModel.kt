package com.smitcoderx.convene.Ui.License

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smitcoderx.convene.Model.LicenseDataModel
import com.smitcoderx.convene.Utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LicenseViewModel @Inject constructor(
    private val repository: LicenseRepository
): ViewModel() {

    val isNetworkConnected = MutableLiveData<Boolean>()
    private val _updateLicenseCertification = MutableLiveData<Resource<String>>()
    val updateLicenseCertification: LiveData<Resource<String>>
        get() = _updateLicenseCertification

    private val _fetchLicenseCertification = MutableLiveData<Resource<ArrayList<LicenseDataModel?>>>()
    val fetchLicenseCertification: LiveData<Resource<ArrayList<LicenseDataModel?>>>
        get() = _fetchLicenseCertification

    fun addLicenseAndCertification(id: String, licenseDataModel: LicenseDataModel) = viewModelScope.launch {
        _updateLicenseCertification.value = Resource.Loading()
        if(isNetworkConnected.value == true) {
            _updateLicenseCertification.value = Resource.Loading()
            val result = repository.addLicenseAndCertification(id, licenseDataModel)
            _updateLicenseCertification.value = result
        } else {
            _updateLicenseCertification.value = Resource.Error("No Internet Connection")
        }
    }

    fun fetchLicenseCertification(id: String) = viewModelScope.launch {
        _fetchLicenseCertification.value = Resource.Loading()
        if(isNetworkConnected.value == true) {
            _fetchLicenseCertification.value = Resource.Loading()
            val result = repository.fetchLicenseAndCertification(id)
            _fetchLicenseCertification.value = result
        } else {
            _fetchLicenseCertification.value = Resource.Error("No Internet Connection")
        }
    }
}