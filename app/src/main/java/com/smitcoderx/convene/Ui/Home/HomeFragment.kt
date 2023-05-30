package com.smitcoderx.convene.Ui.Home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.firebase.ktx.Firebase
import com.smitcoderx.convene.R
import com.smitcoderx.convene.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)


    }
}