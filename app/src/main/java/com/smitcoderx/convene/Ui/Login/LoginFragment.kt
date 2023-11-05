package com.smitcoderx.convene.Ui.Login

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.smitcoderx.convene.R
import com.smitcoderx.convene.Ui.Login.Models.LoginData
import com.smitcoderx.convene.Utils.ConnectionLiveData
import com.smitcoderx.convene.Utils.Constants.TAG
import com.smitcoderx.convene.Utils.Resource
import com.smitcoderx.convene.Utils.drawableToBitmap
import com.smitcoderx.convene.Utils.isConnected
import com.smitcoderx.convene.Utils.morphDoneAndRevert
import com.smitcoderx.convene.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val loginViewModel by viewModels<LoginViewModel>()
    private lateinit var connectionLiveData: ConnectionLiveData

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        connectionLiveData = ConnectionLiveData(requireContext())

        connectionLiveData.observe(requireActivity()) {
            loginViewModel.isNetworkConnectedLiveData.value = it
        }
        loginViewModel.isNetworkConnectedLiveData.value = context?.isConnected

        binding.btnLogin.setOnClickListener {
            loginViewModel.signInAccount(
                binding.etEmailLogin.text.toString(),
                binding.etPasswordLogin.text.toString()
            )
        }




        loginViewModel.signInMutableLiveData.observe(requireActivity()) {
            when (it) {
                is Resource.Success -> {
                    context?.let { it1 ->
                        binding.btnLogin.morphDoneAndRevert(
                            requireContext(),
                            it1.getColor(R.color.accent_color),
                            drawableToBitmap(context?.getDrawable(R.drawable.ic_success)!!)
                        ) {
                            val loginData = LoginData(
                                it.data?.displayName.toString(),
                                it.data?.email.toString(),
                                it.data?.uid.toString(),
                                it.data?.phoneNumber.toString(),
                                it.data?.photoUrl.toString(),
                                null
                            )
                            val action = LoginFragmentDirections.actionLoginFragmentToActionHome(loginData)
                            findNavController().navigate(action)
                        }
                    }

                }

                is Resource.Error -> {
                    context?.let { it1 ->
                        binding.btnLogin.morphDoneAndRevert(
                            requireContext(),
                            it1.getColor(R.color.accent_color),
                            drawableToBitmap(context?.getDrawable(R.drawable.ic_close)!!)
                        ) {
                            Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                }

                is Resource.Loading -> {
                    Log.d(TAG, "IsLoading: ....")
                }
            }
        }

        binding.tvNoAcc.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(action)
        }
    }

    /*  private fun showLoading() {
          binding.loadingBg.visibility = View.VISIBLE
          binding.pgLoading.visibility = View.VISIBLE
      }

      private fun hideLoading() {
          binding.loadingBg.visibility = View.GONE
          binding.pgLoading.visibility = View.GONE
      }*/

}