package com.datingapp.app.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.datingapp.app.databinding.FragmentSignupStepDistanceBinding
import com.datingapp.app.ui.SignupActivity

class SignupStepDistanceFragment : Fragment() {

    private var _binding: FragmentSignupStepDistanceBinding? = null
    private val binding get() = _binding!!

    // Example distances in km
    private val distances = listOf("5 km", "10 km", "20 km", "50 km", "100 km")
    private var selectedDistance = distances[2] // default 20 km

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupStepDistanceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupDistanceOptions()
        binding.btnNext.setOnClickListener {
            (activity as? SignupActivity)?.nextStep()
        }
    }

    private fun setupDistanceOptions() {
        binding.rgDistance.removeAllViews()
        distances.forEach { distance ->
            val radioButton = androidx.appcompat.widget.AppCompatRadioButton(requireContext()).apply {
                text = distance
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
            binding.rgDistance.addView(radioButton)
        }

        binding.rgDistance.setOnCheckedChangeListener { group, checkedId ->
            val rb = group.findViewById<androidx.appcompat.widget.AppCompatRadioButton>(checkedId)
            selectedDistance = rb.text.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
