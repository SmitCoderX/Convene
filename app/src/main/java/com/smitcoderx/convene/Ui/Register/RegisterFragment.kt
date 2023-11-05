package com.smitcoderx.convene.Ui.Register

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.smitcoderx.convene.R
import com.smitcoderx.convene.Utils.ConnectionLiveData
import com.smitcoderx.convene.Utils.Resource
import com.smitcoderx.convene.Utils.drawableToBitmap
import com.smitcoderx.convene.Utils.isConnected
import com.smitcoderx.convene.Utils.morphDoneAndRevert
import com.smitcoderx.convene.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var binding: FragmentRegisterBinding
    private val viewModel by viewModels<RegisterViewModel>()
    private lateinit var connectionLiveData: ConnectionLiveData

    @SuppressLint("UseCompatLoadingForDrawables")
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
                    binding.etPasswordLogin.text.toString(),
                    binding.etMobileNo.text.toString()
                )
        }

        binding.tvNoAcc.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.createAccountLiveData.observe(requireActivity()) {
            when (it) {
                is Resource.Success -> {
                    context?.let { it1 ->
                        binding.btnLogin.morphDoneAndRevert(
                            requireContext(),
                            it1.getColor(R.color.accent_color),
                            drawableToBitmap(context?.getDrawable(R.drawable.ic_success)!!)
                        ) {
                            val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
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
                        ) {}
                    }
                    Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                }

                is Resource.Loading -> {

                }
            }
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