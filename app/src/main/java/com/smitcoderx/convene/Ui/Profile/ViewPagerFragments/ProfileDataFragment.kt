package com.smitcoderx.convene.Ui.Profile.ViewPagerFragments

import android.animation.ArgbEvaluator
import android.animation.TimeAnimator
import android.animation.ValueAnimator
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.smitcoderx.convene.Adapter.ExperienceAdapter
import com.smitcoderx.convene.R
import com.smitcoderx.convene.Ui.Education.EducationViewModel
import com.smitcoderx.convene.Ui.Experience.ExperienceViewModel
import com.smitcoderx.convene.Ui.Login.Models.LoginData
import com.smitcoderx.convene.Ui.Profile.ProfileViewModel
import com.smitcoderx.convene.Ui.Recommendation.RecommendationBottomSheetFragment
import com.smitcoderx.convene.Utils.ConnectionLiveData
import com.smitcoderx.convene.Utils.Constants.TAG
import com.smitcoderx.convene.Utils.Resource
import com.smitcoderx.convene.Utils.isConnected
import com.smitcoderx.convene.databinding.FragmentProfileDataBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileDataFragment : Fragment(R.layout.fragment_profile_data) {

    private lateinit var binding: FragmentProfileDataBinding
    private lateinit var connectionLiveData: ConnectionLiveData
    private val viewModel by viewModels<ProfileViewModel>()
    private val experienceViewModel by viewModels<ExperienceViewModel>()
    private val educationViewModel by viewModels<EducationViewModel>()
    private val user = Firebase.auth.currentUser
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileDataBinding.bind(view)
        connectionLiveData = ConnectionLiveData(requireContext())

        connectionLiveData.observe(requireActivity()) {
            viewModel.isNetworkConnectedLiveData.value = it
            experienceViewModel.isNetworkConnectedLiveData.value = it
            educationViewModel.isNetworkConnectedLiveData.value = it
        }

        viewModel.isNetworkConnectedLiveData.value = context?.isConnected
        experienceViewModel.isNetworkConnectedLiveData.value = context?.isConnected
        educationViewModel.isNetworkConnectedLiveData.value = context?.isConnected

        viewModel.fetchUserDetails(user?.uid.toString())
        experienceViewModel.getAllExperience(user?.uid.toString())
        educationViewModel.fetchEducationDetails(user?.uid.toString())

        val experienceAdapter = ExperienceAdapter()

        var loginData = LoginData()

        viewModel.fetchProfileDataLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    loginData = it.data!!
                    Log.i(TAG, "FetchProfileData: ${it.data}")
                }

                is Resource.Error -> {
                    Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                    Log.e(TAG, "ProfileDataFragment: ${it.message.toString()}")
                }

                is Resource.Loading -> {
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                    Log.i(TAG, "ProfileDataFragment: Loading")
                }
            }
        }

        experienceViewModel.fetchJobData.observe(viewLifecycleOwner) { res ->
            when (res) {
                is Resource.Success -> {
                    Log.i(TAG, "ExperienceDataFragment: ${res.data}")

                    if (res.data.isNullOrEmpty()) {
                        binding.ivEmptyAddBtn.visibility = View.VISIBLE
                        binding.tvAddExpTitle.visibility = View.VISIBLE
                        binding.rvExperience.visibility = View.GONE
                        binding.ivAddExp.visibility = View.GONE
                        binding.ivEditExp.visibility = View.GONE
                    } else {
                        binding.ivEmptyAddBtn.visibility = View.GONE
                        binding.tvAddExpTitle.visibility = View.GONE
                        binding.rvExperience.visibility = View.VISIBLE
                        binding.ivAddExp.visibility = View.VISIBLE
                        binding.ivEditExp.visibility = View.VISIBLE
                        experienceAdapter.differ.submitList(res.data)
                        binding.rvExperience.apply {
                            setHasFixedSize(true)
                            adapter = experienceAdapter
                        }
                    }
                }

                is Resource.Error -> {
                    Toast.makeText(requireContext(), res.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                    Log.e(TAG, "ExperienceDataFragment: ${res.message.toString()}")
                }

                is Resource.Loading -> {
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                    Log.i(TAG, "ExperienceDataFragment: Loading")
                }
            }
        }

        educationViewModel.fetchEducation.observe(viewLifecycleOwner) { res ->
            when (res) {
                is Resource.Success -> {
                    Log.i(TAG, "ExperienceDataFragment: ${res.data}")

                    if (res.data.isNullOrEmpty()) {
                        binding.ivEmptyAddBtnEdu.visibility = View.VISIBLE
                        binding.tvAddEduTitle.visibility = View.VISIBLE
                        binding.rvEducation.visibility = View.GONE
                        binding.ivAddEdu.visibility = View.GONE
                        binding.ivEditEdu.visibility = View.GONE
                    } else {
                        binding.ivEmptyAddBtnEdu.visibility = View.GONE
                        binding.tvAddEduTitle.visibility = View.GONE
                        binding.rvEducation.visibility = View.VISIBLE
                        binding.ivAddEdu.visibility = View.VISIBLE
                        binding.ivEditEdu.visibility = View.VISIBLE
//                        experienceAdapter.differ.submitList(res.data)
                        binding.rvExperience.apply {
                            setHasFixedSize(true)
                            adapter = experienceAdapter
                        }
                    }
                }

                is Resource.Error -> {
                    Toast.makeText(requireContext(), res.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                    Log.e(TAG, "ExperienceDataFragment: ${res.message.toString()}")
                }

                is Resource.Loading -> {
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                    Log.i(TAG, "ExperienceDataFragment: Loading")
                }
            }
        }

        binding.ivEmptyAddBtn.setOnClickListener {
            findNavController().navigate(R.id.experienceFragment)
        }

        binding.ivAddExp.setOnClickListener {
            findNavController().navigate(R.id.experienceFragment)
        }

        binding.ivEditExp.setOnClickListener {
            findNavController().navigate(R.id.experienceFragment)
        }

        binding.ivEmptyAddBtnEdu.setOnClickListener {
            findNavController().navigate(R.id.educationFragment)
        }

        binding.ivEmptyAddBtnLandc.setOnClickListener {
            findNavController().navigate(R.id.licenseFragment)
        }

        binding.ivEmptyAddBtnProjects.setOnClickListener {
            findNavController().navigate(R.id.projectFragment)
        }

        binding.ivEmptyAddBtnRecommendation.setOnClickListener {
            val recommendChoiceSheet = RecommendationBottomSheetFragment()
            recommendChoiceSheet.show(childFragmentManager, RecommendationBottomSheetFragment.TAG)
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