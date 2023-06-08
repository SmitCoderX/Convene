package com.smitcoderx.convene.Ui.Profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.smitcoderx.convene.Adapter.FollowListAdapter
import com.smitcoderx.convene.R
import com.smitcoderx.convene.Ui.Login.Models.LoginData
import com.smitcoderx.convene.Ui.Profile.ViewPagerFragments.ActivityFragment
import com.smitcoderx.convene.Ui.Profile.ViewPagerFragments.CommunityDataFragment
import com.smitcoderx.convene.Ui.Profile.ViewPagerFragments.ConnectionDataFragment
import com.smitcoderx.convene.Ui.Profile.ViewPagerFragments.ProfileDataFragment
import com.smitcoderx.convene.Utils.ConnectionLiveData
import com.smitcoderx.convene.Utils.Constants.IMAGE_URL
import com.smitcoderx.convene.Utils.Constants.MULTI_VAR
import com.smitcoderx.convene.Utils.ViewPagerAdapter
import com.smitcoderx.convene.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs


@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile), AppBarLayout.OnOffsetChangedListener {

    private lateinit var binding: FragmentProfileBinding
    private var mIsTheTitleVisible = false
    private var mIsTheTitleContainerVisible = true
    private val user = Firebase.auth.currentUser
    private lateinit var connectionLiveData: ConnectionLiveData
    private val viewModel by viewModels<ProfileViewModel>()


    companion object {
        const val PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR = 0.9f
        const val PERCENTAGE_TO_HIDE_TITLE_DETAILS = 0.3f
        const val ALPHA_ANIMATIONS_DURATION = 200
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

        addViewPagers()

        binding.mainAppbar.addOnOffsetChangedListener(this)
        startAlphaAnimation(binding.mainTextviewTitle, 0, View.INVISIBLE)

        binding.tvUsername.text = user?.displayName
        binding.mainTextviewTitle.text = user?.displayName
        binding.tvFollowTitle.text = "${user?.displayName} Follow's"
        Glide.with(requireActivity())
            .load(user?.photoUrl)
            .into(binding.ivUserImage)

        val adapterF = FollowListAdapter()
        val loginData = mutableListOf<LoginData>()
        val userList = arrayListOf(
            "Abby",
            "Pumpkin",
            "Max",
            "Buddy",
            "Mia",
            "Daisy",
            "Gracie",
            "Cookie",
            "Ginger",
            "Jack",
            "Charlie",
            "Callie",
            "Willow",
            "Missy",
            "Angel",
            "Rocky",
            "Misty",
            "Lucky",
            "Scooter",
            "Lucy"
        )
        for (i in userList.indices) {
            loginData.add(
                LoginData(
                    userList[i],
                    "",
                    "",
                    "",
                    "$IMAGE_URL${userList[i]}.png?apikey=$MULTI_VAR",
                    null
                )
            )
        }

        adapterF.differ.submitList(loginData)
        binding.rvUserFollows.apply {
            setHasFixedSize(true)
            adapter = adapterF
        }
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        val maxScroll = appBarLayout!!.totalScrollRange
        val percentage = abs(verticalOffset).toFloat() / maxScroll.toFloat()

        handleAlphaOnTitle(percentage)
        handleToolbarTitleVisibility(percentage)
    }

    private fun handleToolbarTitleVisibility(percentage: Float) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {
            if (!mIsTheTitleVisible) {
                startAlphaAnimation(
                    binding.mainTextviewTitle,
                    ALPHA_ANIMATIONS_DURATION.toLong(),
                    View.VISIBLE
                )
                mIsTheTitleVisible = true
            }
        } else {
            if (mIsTheTitleVisible) {
                startAlphaAnimation(
                    binding.mainTextviewTitle,
                    ALPHA_ANIMATIONS_DURATION.toLong(),
                    View.INVISIBLE
                )
                mIsTheTitleVisible = false
            }
        }
    }

    private fun handleAlphaOnTitle(percentage: Float) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if (mIsTheTitleContainerVisible) {
                startAlphaAnimation(
                    binding.mainLinearlayoutTitle,
                    ALPHA_ANIMATIONS_DURATION.toLong(),
                    View.INVISIBLE
                )
                mIsTheTitleContainerVisible = false
            }
        } else {
            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(
                    binding.mainLinearlayoutTitle,
                    ALPHA_ANIMATIONS_DURATION.toLong(),
                    View.VISIBLE
                )
                mIsTheTitleContainerVisible = true
            }
        }
    }

    private fun startAlphaAnimation(v: View, duration: Long, visibility: Int) {
        val alphaAnimation =
            if (visibility == View.VISIBLE) AlphaAnimation(0f, 1f) else AlphaAnimation(1f, 0f)
        alphaAnimation.duration = duration
        alphaAnimation.fillAfter = true
        v.startAnimation(alphaAnimation)
    }

    private fun addViewPagers() {
        val fragmentList = arrayListOf(
            ProfileDataFragment().newInstance(),
            ActivityFragment().newInstance(),
            CommunityDataFragment().newInstance(),
            ConnectionDataFragment().newInstance()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            childFragmentManager,
            lifecycle
        )

        binding.upcomingViewPager.adapter = adapter

        TabLayoutMediator(binding.upcomingTabLayout, binding.upcomingViewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.profile)
                1 -> tab.text = getString(R.string.activity)
                2 -> tab.text = getString(R.string.community)
                3 -> tab.text = getString(R.string.connection)
            }
        }.attach()
    }
}