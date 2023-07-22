package com.smitcoderx.convene.Ui.Recommendation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smitcoderx.convene.R
import com.smitcoderx.convene.Utils.Constants.ASK
import com.smitcoderx.convene.Utils.Constants.GIVE
import com.smitcoderx.convene.Utils.Constants.KEY
import com.smitcoderx.convene.databinding.FragmentRecommendationBottomsheetBinding

class RecommendationBottomSheetFragment :
    BottomSheetDialogFragment(R.layout.fragment_recommendation_bottomsheet) {

    private lateinit var binding: FragmentRecommendationBottomsheetBinding

    companion object {
        const val TAG = "RecommendationBottomSheet"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRecommendationBottomsheetBinding.bind(view)

        binding.tvAsk.setOnClickListener {
            sendToRecommendation(ASK)
        }

        binding.tvGive.setOnClickListener {
            sendToRecommendation(GIVE)
        }


    }

    private fun sendToRecommendation(tag: String) {
        val bundle = Bundle()
        bundle.putString(KEY, tag)
        findNavController().navigate(R.id.recommendationFragment, bundle)
        dismiss()
    }

}