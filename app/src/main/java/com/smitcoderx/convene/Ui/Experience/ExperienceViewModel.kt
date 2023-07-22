package com.smitcoderx.convene.Ui.Experience

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smitcoderx.convene.Model.ExperienceDataModel
import com.smitcoderx.convene.Utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExperienceViewModel @Inject constructor(
    private val repository: ExperienceRepository
) : ViewModel() {

    val isNetworkConnectedLiveData = MutableLiveData<Boolean>()
    private val _updateExperience = MutableLiveData<Resource<String>>()
    private val _fetchJobData = MutableLiveData<Resource<ArrayList<ExperienceDataModel?>>>()
    val updateExperience: LiveData<Resource<String>>
        get() = _updateExperience

    val fetchJobData: LiveData<Resource<ArrayList<ExperienceDataModel?>>>
        get() = _fetchJobData

    fun getAllExperience(id: String) =
        viewModelScope.launch {
            _fetchJobData.value = Resource.Loading()
            if (isNetworkConnectedLiveData.value == true) {
                _fetchJobData.value = Resource.Loading()
                val result = repository.fetchJobs(id)
                _fetchJobData.value = result
            } else {
                _fetchJobData.value = Resource.Error("No Internet Connection")
            }
        }

    fun updateExperience(id: String, experienceDataModel: ExperienceDataModel) =
        viewModelScope.launch {
            _updateExperience.value = Resource.Loading()
            if (isNetworkConnectedLiveData.value == true) {
                _updateExperience.value = Resource.Loading()
                val result = repository.updateExperience(id, experienceDataModel)
                _updateExperience.value = result
            } else {
                _updateExperience.value = Resource.Error("No Internet Connection")
            }
        }
}