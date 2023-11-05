package com.smitcoderx.convene.Ui.Dashboard

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.smitcoderx.convene.R
import com.smitcoderx.convene.Utils.ConnectionLiveData
import com.smitcoderx.convene.Utils.drawableToBitmap
import com.smitcoderx.convene.Utils.morphDoneAndRevert
import com.smitcoderx.convene.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment(R.layout.fragment_dashboard) {
    private lateinit var binding: FragmentDashboardBinding
    private lateinit var connectionLiveData: ConnectionLiveData


    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDashboardBinding.bind(view)

        connectionLiveData = ConnectionLiveData(requireContext())



        binding.btnStarted.setOnClickListener {
            checkInternet()
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun checkInternet() {
        connectionLiveData.observe(requireActivity()) {
            when (it) {
                true -> {
                    context?.let { it1 ->
                        binding.btnStarted.morphDoneAndRevert(
                            requireContext(),
                            it1.getColor(R.color.accent_color),
                            drawableToBitmap(context?.getDrawable(R.drawable.ic_success)!!)
                        ) {
                            val action = DashboardFragmentDirections.actionDashboardFragmentToLoginFragment()
                            findNavController().navigate(action)
                        }
                    }

                }

                false -> {
                    context?.let { it1 ->
                        binding.btnStarted.morphDoneAndRevert(
                            requireContext(),
                            it1.getColor(R.color.accent_color),
                            drawableToBitmap(context?.getDrawable(R.drawable.ic_close)!!)
                        ) {}
                    }
                }
            }

        }
    }

}