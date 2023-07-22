package com.smitcoderx.convene.Ui.Profile.ViewPagerFragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.smitcoderx.convene.R
import com.smitcoderx.convene.databinding.FragmentConnectionDataBinding

class ConnectionDataFragment : Fragment(R.layout.fragment_connection_data) {

    private lateinit var binding: FragmentConnectionDataBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentConnectionDataBinding.bind(view)
    }

    fun newInstance(): ConnectionDataFragment {
        /*val args = Bundle()
        args.putParcelable(Constants.INFO_DATA, data)
        fragment.arguments = args*/
        return ConnectionDataFragment()
    }

}