package com.datingapp.app.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.datingapp.app.databinding.FragmentSignupStepHeightBinding


class SignupStepHeightFragment : Fragment() {

    private var _binding: FragmentSignupStepHeightBinding? = null
    private val binding get() = _binding!!

    // Example: height range from 100cm to 220cm
    private val minHeight = 100
    private val maxHeight = 220
    private var selectedHeight = 170 // default value

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupStepHeightBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNumberPicker()
        binding.btnNext.setOnClickListener {
            // You can save selectedHeight to your ViewModel or pass it to the Activity
            (activity as? SignupActivity)?.nextStep()
        }
    }

    private fun setupNumberPicker() {
        binding.npHeight.minValue = minHeight
        binding.npHeight.maxValue = maxHeight
        binding.npHeight.value = selectedHeight

        binding.npHeight.setOnValueChangedListener { _, _, newVal ->
            selectedHeight = newVal
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
