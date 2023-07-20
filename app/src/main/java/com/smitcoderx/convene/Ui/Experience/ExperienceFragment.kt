package com.smitcoderx.convene.Ui.Experience

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.smitcoderx.convene.Model.ExperienceDataModel
import com.smitcoderx.convene.R
import com.smitcoderx.convene.Utils.ConnectionLiveData
import com.smitcoderx.convene.Utils.Resource
import com.smitcoderx.convene.Utils.isConnected
import com.smitcoderx.convene.databinding.FragmentExperienceBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExperienceFragment : Fragment(R.layout.fragment_experience) {

    private lateinit var binding: FragmentExperienceBinding
    private lateinit var connectionLiveData: ConnectionLiveData
    private val viewModel by viewModels<ExperienceViewModel>()
    private val user = Firebase.auth.currentUser

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentExperienceBinding.bind(view)

        connectionLiveData = ConnectionLiveData(requireContext())

        connectionLiveData.observe(requireActivity()) {
            viewModel.isNetworkConnectedLiveData.value = it
        }
        viewModel.isNetworkConnectedLiveData.value = context?.isConnected


        binding.btnSave.setOnClickListener {
            val experienceDataModel = ExperienceDataModel(
                user?.uid.toString(),
                binding.etTitle.text.toString(),
                binding.etCompanyName.text.toString(),
                binding.etStartDate.text.toString(),
                "",
                binding.etEndDate.text.toString(),
                "",
                arrayListOf(),
                binding.etDescription.text.toString(),
                binding.etEmployment.text.toString(),
                binding.etLocation.text.toString(),
                binding.tvCurrentlyWork.isChecked,
                binding.etLocationType.text.toString(),
                binding.etIndustry.text.toString()
            )
            viewModel.updateExperience(user?.uid.toString(), experienceDataModel)
        }

        viewModel.updateExperience.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    hideLoading()
                    Toast.makeText(requireContext(), it.data.toString(), Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }

                is Resource.Error -> {
                    hideLoading()
                    Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                }

                is Resource.Loading -> {
                    showLoading()
                    Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        binding.scroll.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY > oldScrollY) {
                binding.btnSave.hide();
            } else if (scrollY < 30) {
                binding.btnSave.show();
            } else {
                binding.btnSave.show();
            }
        }
    }

    private fun showLoading() {
        binding.loadingBg.visibility = View.VISIBLE
        binding.pgLoading.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.loadingBg.visibility = View.GONE
        binding.pgLoading.visibility = View.GONE
    }

}