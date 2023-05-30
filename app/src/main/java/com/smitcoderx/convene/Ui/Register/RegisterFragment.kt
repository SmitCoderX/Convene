package com.smitcoderx.convene.Ui.Register

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.smitcoderx.convene.R
import com.smitcoderx.convene.Utils.ConnectionLiveData
import com.smitcoderx.convene.Utils.Constants.TAG
import com.smitcoderx.convene.Utils.Resource
import com.smitcoderx.convene.Utils.isConnected
import com.smitcoderx.convene.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var binding: FragmentRegisterBinding
    private val viewModel by viewModels<RegisterViewModel>()
    private lateinit var connectionLiveData: ConnectionLiveData

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)

        connectionLiveData = ConnectionLiveData(requireContext())

        connectionLiveData.observe(requireActivity()) {
            viewModel.isNetworkConnectedLiveData.value = it
        }
        viewModel.isNetworkConnectedLiveData.value = context?.isConnected

        binding.btnLogin.setOnClickListener {
            viewModel.createAccountEmail(
                binding.etUsernameLogin.text.toString(),
                binding.etEmailLogin.text.toString(),
                binding.etPasswordLogin.text.toString()
            )
        }

        viewModel.createAccountLiveData.observe(requireActivity()) {
            when (it) {
                is Resource.Success -> {
                    showLoading(false)
                    Log.d(TAG, "RegisterFragmentData: ${it.data}")
                    Log.d(TAG, "RegisterFragment: ${it.data?.displayName}")
                }

                is Resource.Error -> {
                    showLoading(false)
                    Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                }

                is Resource.Loading -> {
                    showLoading(true)
                }
            }
        }

    }

    private fun showLoading(flag: Boolean) {
        if (flag) {
            binding.loadingBg.visibility = View.VISIBLE
            binding.pgLoading.visibility = View.VISIBLE
        }

        binding.loadingBg.visibility = View.GONE
        binding.pgLoading.visibility = View.GONE
    }

}