package com.smitcoderx.convene.Ui.Home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.View
import android.widget.TextSwitcher
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.textview.MaterialTextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.smitcoderx.convene.R
import com.smitcoderx.convene.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private val args by navArgs<HomeFragmentArgs>()
    private val db = Firebase.firestore
    private var index = 0
    private lateinit var text: MaterialTextView
    private val greetings = mutableListOf<String>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        val data = args.loginData
//        Glide.with(requireContext()).load(data?.photoUrl).into(binding.ivProfile)

        greetings.add(generateGreeting(data?.displayName.toString()))
        greetings.add(context?.getString(R.string.app_name).toString())

        setupGreetingTextSwitcher(greetings)
    }

    private fun generateGreeting(username: String): String {
        val c = Calendar.getInstance()
        val timeOfDay: Int = c.get(Calendar.HOUR_OF_DAY)
        var greeting = ""

        when (timeOfDay) {
            in 0..11 -> {
                greeting = "Good Morning"
            }

            in 12..15 -> {
                greeting = "Good Afternoon"
            }

            in 16..20 -> {
                greeting = "Good Evening"
            }

            in 21..23 -> {
                greeting = "Good Night"
            }
        }

        return "$greeting,$username"
    }

    private fun setupGreetingTextSwitcher(list: MutableList<String>) {
        binding.textSwitcher.setFactory {
            text = MaterialTextView(requireContext())
            text.textSize = 20F
            context?.let { text.setTextColor(it.getColor(R.color.black)) }
            text.gravity = Gravity.START
            text.typeface = ResourcesCompat.getFont(requireContext(), R.font.poppins_medium)
            text
        }

        binding.textSwitcher.setInAnimation(requireContext(), android.R.anim.slide_in_left)
        binding.textSwitcher.setOutAnimation(requireContext(), R.anim.move_up)
        binding.textSwitcher.setText(list[index])


        Handler(Looper.getMainLooper()).postDelayed({
            if (index == list.size - 1) {
                index = 0
                binding.textSwitcher.setText(list[index])
            } else {
                // otherwise display next string in array
                binding.textSwitcher.setText(list[++index])
            }
        }, 3000)
    }

}