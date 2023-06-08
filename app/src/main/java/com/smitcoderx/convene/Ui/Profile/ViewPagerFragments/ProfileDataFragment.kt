package com.smitcoderx.convene.Ui.Profile.ViewPagerFragments

import android.animation.ArgbEvaluator
import android.animation.TimeAnimator
import android.animation.ValueAnimator
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.smitcoderx.convene.R
import com.smitcoderx.convene.Ui.Experience.ExperienceFragment
import com.smitcoderx.convene.Ui.Profile.ProfileViewModel
import com.smitcoderx.convene.Utils.ConnectionLiveData
import com.smitcoderx.convene.Utils.isConnected
import com.smitcoderx.convene.databinding.FragmentProfileDataBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileDataFragment : Fragment(R.layout.fragment_profile_data) {

    private lateinit var binding: FragmentProfileDataBinding
    private lateinit var connectionLiveData: ConnectionLiveData
    private val viewModel by viewModels<ProfileViewModel>()
    private val user = Firebase.auth.currentUser
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileDataBinding.bind(view)
        connectionLiveData = ConnectionLiveData(requireContext())

        connectionLiveData.observe(requireActivity()) {
            viewModel.isNetworkConnectedLiveData.value = it
        }
        viewModel.isNetworkConnectedLiveData.value = context?.isConnected

        /*viewModel.updateUserDetails(
            user?.uid.toString(),
            LoginData(
                user?.displayName.toString(),
                user?.email.toString(),
                user?.uid.toString(),
                user?.phoneNumber.toString(),
                user?.photoUrl.toString(),
                ProfileDataModel(
                    user?.uid.toString(),
                    "",
                    null,
                    "",
                    0,
                    null,
                    null,
                    0,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    arrayListOf("1", "2", "3" ,"4", "5")
                )
            )
        )

        viewModel.profileDataLiveData.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Success -> {
                    Toast.makeText(requireContext(), "Data Saved", Toast.LENGTH_SHORT).show()
                    Log.i(TAG, "ProfileDataFragment: Data Saved")
                }

                is Resource.Error -> {
                    Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_SHORT).show()
                    Log.e(TAG, "ProfileDataFragment: ${it.message.toString()}")
                }

                is Resource.Loading -> {
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                    Log.i(TAG, "ProfileDataFragment: Loading")
                }
            }
        }*/

        binding.ivEmptyAddBtn.setOnClickListener {
            findNavController().navigate(R.id.experienceFragment)
        }

        binding.ivEmptyAddBtnEdu.setOnClickListener {
        }

        setAnimatedGradient()
    }

    fun newInstance(): ProfileDataFragment {
        /*val args = Bundle()
        args.putParcelable(Constants.INFO_DATA, data)
        fragment.arguments = args*/
        return ProfileDataFragment()
    }

    private fun setAnimatedGradient() {
        val start = resources.getColor(android.R.color.holo_purple)
        val mid = resources.getColor(R.color.purple)
        val end = resources.getColor(R.color.dark_purple)

        val gradient = binding.conCourse.background as GradientDrawable

        val evaluator = ArgbEvaluator()
        val animator = TimeAnimator.ofFloat(0.0f, 1.0f)
        animator.duration = 1500
        animator.repeatCount = ValueAnimator.INFINITE
        animator.repeatMode = ValueAnimator.REVERSE
        animator.addUpdateListener {
            val fraction = it.animatedFraction
            val newStart = evaluator.evaluate(fraction, start, end) as Int
            val newMid = evaluator.evaluate(fraction, mid, start) as Int
            val newEnd = evaluator.evaluate(fraction, end, mid) as Int

            gradient.colors = intArrayOf(newStart, newMid, newEnd)
        }
        animator.start()
    }

}