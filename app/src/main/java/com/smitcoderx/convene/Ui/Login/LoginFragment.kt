package com.smitcoderx.convene.Ui.Login

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.smitcoderx.convene.R
import com.smitcoderx.convene.Utils.ConnectionLiveData
import com.smitcoderx.convene.Utils.Constants.TAG
import com.smitcoderx.convene.Utils.Resource
import com.smitcoderx.convene.Utils.isConnected
import com.smitcoderx.convene.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val loginViewModel by viewModels<LoginViewModel>()
    private lateinit var connectionLiveData: ConnectionLiveData

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        connectionLiveData = ConnectionLiveData(requireContext())

        connectionLiveData.observe(requireActivity()) {
            loginViewModel.isNetworkConnectedLiveData.value = it
        }
        loginViewModel.isNetworkConnectedLiveData.value = context?.isConnected


        binding.btnLogin.setOnClickListener {
            loginViewModel.signInEmail(
                binding.etEmailLogin.text.toString(),
                binding.etPasswordLogin.text.toString()
            ).observe(viewLifecycleOwner) {
                Log.d(TAG, "LoginFragment: ${it.data?.displayName + it.message.toString()}")
                when(it) {
                    is Resource.Success -> {
//                        Toast.makeText(requireContext(), it.data?.displayName, Toast.LENGTH_SHORT).show()
//                    val action = LoginFragmentDirections.actionLoginFragmentToActionHome()
//                    findNavController().navigate(action)
                    }

                    is Resource.Error -> {
                        Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_SHORT).show()
                    }

                    is Resource.Loading -> {

                    }
                }
            }
        }

       /* loginViewModel.signInLiveData.observe(requireActivity()) {

        }*/

        binding.tvNoAcc.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(action)
        }

    }
}