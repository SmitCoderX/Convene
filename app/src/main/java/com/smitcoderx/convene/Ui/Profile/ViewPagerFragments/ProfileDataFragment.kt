package com.smitcoderx.convene.Ui.Profile.ViewPagerFragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.smitcoderx.convene.R
import com.smitcoderx.convene.databinding.FragmentProfileDataBinding

class ProfileDataFragment : Fragment(R.layout.fragment_profile_data) {

    private lateinit var binding: FragmentProfileDataBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileDataBinding.bind(view)
    }

    fun newInstance(): ProfileDataFragment {
        /*val args = Bundle()
        args.putParcelable(Constants.INFO_DATA, data)
        fragment.arguments = args*/
        return ProfileDataFragment()
    }


}