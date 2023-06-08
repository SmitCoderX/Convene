package com.smitcoderx.convene.Ui.Experience

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.smitcoderx.convene.R
import com.smitcoderx.convene.databinding.FragmentExperienceBinding

class ExperienceFragment: Fragment(R.layout.fragment_experience) {

    private lateinit var binding: FragmentExperienceBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentExperienceBinding.bind(view)

        binding.scroll.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY > oldScrollY) {
                binding.btnSave.hide();
            } else if (scrollY < 30) {
                binding.btnSave.show();
            } else {
                binding.btnSave.show();
            }
        }
    }

}