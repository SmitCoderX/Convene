package com.smitcoderx.convene.Ui.Connection

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.smitcoderx.convene.R
import com.smitcoderx.convene.databinding.FragmentConnectionsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConnectionsFragment : Fragment(R.layout.fragment_connections) {

    private lateinit var binding: FragmentConnectionsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentConnectionsBinding.bind(view)


    }
}