package com.smitcoderx.convene.Ui.License

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.smitcoderx.convene.Model.LicenseDataModel
import com.smitcoderx.convene.R
import com.smitcoderx.convene.Utils.ConnectionLiveData
import com.smitcoderx.convene.Utils.Resource
import com.smitcoderx.convene.Utils.isConnected
import com.smitcoderx.convene.databinding.FragmentLicenseBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LicenseFragment : Fragment(R.layout.fragment_license) {

    private lateinit var binding: FragmentLicenseBinding
    private lateinit var connectionLiveData: ConnectionLiveData
    private val licenseViewModel by viewModels<LicenseViewModel>()
    private val user = Firebase.auth.currentUser

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLicenseBinding.bind(view)

        connectionLiveData = ConnectionLiveData(requireContext())

        licenseViewModel.isNetworkConnected.value = context?.isConnected

        binding.btnSave.setOnClickListener {
            val licenseDataModel = LicenseDataModel(
                "",
                binding.etName.text.toString(),
                binding.etIssueOrg.text.toString(),
                "",
                binding.etIssueDate.text.toString(),
                binding.etExpirationDate.text.toString(),
                binding.etCredentialId.text.toString(),
                binding.etCredentialUrl.text.toString(),
                arrayListOf()
            )

            licenseViewModel.addLicenseAndCertification(user?.uid.toString(), licenseDataModel)
        }

        licenseViewModel.updateLicenseCertification.observe(viewLifecycleOwner) {
            when(it) {
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