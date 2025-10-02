package com.datingapp.app.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.datingapp.app.databinding.FragmentSignupStepAgeGroupBinding

class SignupStepAgeGroupFragment : Fragment() {

    private var _binding: FragmentSignupStepAgeGroupBinding? = null
    private val binding get() = _binding!!

    // Example age ranges
    private val ageGroups = listOf("18-25", "26-35", "36-45", "46-60", "60+")
    private var selectedAgeGroup = ageGroups[0]

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupStepAgeGroupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAgeGroupOptions()
        binding.btnFinish.setOnClickListener {
            // Complete signup here
            // Example: (activity as? SignupActivity)?.finishSignup()
        }
    }

    private fun setupAgeGroupOptions() {
        binding.rgAgeGroup.removeAllViews()
        ageGroups.forEach { ageRange ->
            val radioButton = androidx.appcompat.widget.AppCompatRadioButton(requireContext()).apply {
                text = ageRange
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
            binding.rgAgeGroup.addView(radioButton)
        }

        binding.rgAgeGroup.setOnCheckedChangeListener { group, checkedId ->
            val rb = group.findViewById<androidx.appcompat.widget.AppCompatRadioButton>(checkedId)
            selectedAgeGroup = rb.text.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
