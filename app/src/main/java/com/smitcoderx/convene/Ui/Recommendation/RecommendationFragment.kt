package com.smitcoderx.convene.Ui.Recommendation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.smitcoderx.convene.R
import com.smitcoderx.convene.Utils.Constants.ASK
import com.smitcoderx.convene.Utils.Constants.KEY
import com.smitcoderx.convene.databinding.FragmentRecommendationBinding

class RecommendationFragment : Fragment(R.layout.fragment_recommendation) {

    private val args by navArgs<RecommendationFragmentArgs>()
    private lateinit var binding: FragmentRecommendationBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRecommendationBinding.bind(view)
        val type = arguments?.getString(KEY)

        if (type == ASK) {
            binding.recommendTitleCustom.text = "Ask SMIT for a recommendation"
        } else {
            binding.recommendTitleCustom.text = "Write SMIT a recommendation"
        }

    }

}