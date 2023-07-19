package com.smitcoderx.convene.Ui.Education

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.smitcoderx.convene.R
import com.smitcoderx.convene.databinding.FragmentEducationBinding

class EducationFragment : Fragment(R.layout.fragment_education) {

    private lateinit var binding: FragmentEducationBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEducationBinding.bind(view)

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