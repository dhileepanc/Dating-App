package com.datingapp.app.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.datingapp.app.databinding.FragmentSignupStepHeightBinding
import com.datingapp.app.di.viewmodel.SignupViewModel
import com.datingapp.app.ui.SignupActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue


@AndroidEntryPoint
class SignupStepHeightFragment : Fragment() {

    private var _binding: FragmentSignupStepHeightBinding? = null
    private val binding get() = _binding!!

    // Height range
    private val minHeight = 100
    private val maxHeight = 220
    private var selectedHeight = 170 // default

    private val signupViewModel: SignupViewModel by activityViewModels()

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
            // Save current selectedHeight even if user didn't change the picker
            signupViewModel.height = selectedHeight.toString()

            // Move to next step
            (activity as? SignupActivity)?.nextStep()
        }
    }

    private fun setupNumberPicker() {
        binding.npHeight.minValue = minHeight
        binding.npHeight.maxValue = maxHeight
        binding.npHeight.value = selectedHeight

        // Update ViewModel on value change
        binding.npHeight.setOnValueChangedListener { _, _, newVal ->
            selectedHeight = newVal
            signupViewModel.height = newVal.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

