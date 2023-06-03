package com.smitcoderx.convene.Ui.Profile.ViewPagerFragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.smitcoderx.convene.R
import com.smitcoderx.convene.databinding.FragmentActivityBinding

class ActivityFragment: Fragment(R.layout.fragment_activity) {

    private lateinit var binding: FragmentActivityBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentActivityBinding.bind(view)
    }

    fun newInstance(): ActivityFragment {
        /*val args = Bundle()
        args.putParcelable(Constants.INFO_DATA, data)
        fragment.arguments = args*/
        return ActivityFragment()
    }

}