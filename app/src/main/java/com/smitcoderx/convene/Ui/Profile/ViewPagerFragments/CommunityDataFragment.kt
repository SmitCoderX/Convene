package com.smitcoderx.convene.Ui.Profile.ViewPagerFragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.smitcoderx.convene.R
import com.smitcoderx.convene.databinding.FragmentCommunityDataBinding

class CommunityDataFragment : Fragment(R.layout.fragment_community_data) {

    private lateinit var binding: FragmentCommunityDataBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCommunityDataBinding.bind(view)


    }

    fun newInstance(): CommunityDataFragment {
        /*val args = Bundle()
        args.putParcelable(Constants.INFO_DATA, data)
        fragment.arguments = args*/
        return CommunityDataFragment()
    }

}