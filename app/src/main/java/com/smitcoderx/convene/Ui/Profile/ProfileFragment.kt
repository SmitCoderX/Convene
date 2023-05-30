package com.smitcoderx.convene.Ui.Profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.smitcoderx.convene.R
import com.smitcoderx.convene.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment: Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)
    }
}