package com.smitcoderx.convene.Ui.Education

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.smitcoderx.convene.Model.EducationDataModel
import com.smitcoderx.convene.R
import com.smitcoderx.convene.Utils.ConnectionLiveData
import com.smitcoderx.convene.Utils.Resource
import com.smitcoderx.convene.Utils.isConnected
import com.smitcoderx.convene.databinding.FragmentEducationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EducationFragment : Fragment(R.layout.fragment_education) {

    private lateinit var binding: FragmentEducationBinding
    private lateinit var connectionLiveData: ConnectionLiveData
    private val viewModel by viewModels<EducationViewModel>()
    private val user = Firebase.auth.currentUser

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEducationBinding.bind(view)

        connectionLiveData = ConnectionLiveData(requireContext())

        connectionLiveData.observe(requireActivity()) {
            viewModel.isNetworkConnectedLiveData.value = it
        }

        viewModel.isNetworkConnectedLiveData.value = context?.isConnected
        binding.btnSave.setOnClickListener {
            val educationDataModel = EducationDataModel(
                "",
                binding.etSchool.text.toString(),
                binding.etDegree.text.toString(),
                binding.etFos.text.toString(),
                "",
                binding.etStartDate.text.toString(),
                binding.etEndDate.text.toString(),
                binding.etGrade.text.toString(),
                binding.etAnds.text.toString(),
                binding.etDescription.text.toString(),
                arrayListOf()
            )

            viewModel.addEducationData(user?.uid.toString(), educationDataModel)
        }

        viewModel.addEducation.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    hideLoading()
                    Toast.makeText(requireContext(), it.data.toString(), Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }

                is Resource.Loading -> {
                    showLoading()
                    Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                }

                is Resource.Error -> {
                    hideLoading()
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