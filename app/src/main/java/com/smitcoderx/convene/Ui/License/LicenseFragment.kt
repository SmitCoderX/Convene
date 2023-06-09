package com.smitcoderx.convene.Ui.License

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.smitcoderx.convene.R
import com.smitcoderx.convene.databinding.FragmentLicenseBinding

class LicenseFragment: Fragment(R.layout.fragment_license) {

    private lateinit var binding: FragmentLicenseBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLicenseBinding.bind(view)

    }

}