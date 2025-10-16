package com.datingapp.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.datingapp.app.databinding.FragmentSignupStepGenderBinding
import com.datingapp.app.di.viewmodel.SignupViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue


@AndroidEntryPoint
class SignupStepGenderFragment : Fragment() {

    private var _binding: FragmentSignupStepGenderBinding? = null
    private val binding get() = _binding!!

    private val signupViewModel: SignupViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupStepGenderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNext.setOnClickListener {
            val selectedId = binding.rgGender.checkedRadioButtonId
            if (selectedId != -1) {
                // ✅ Use binding to access the selected RadioButton directly
                val selectedButton = binding.root.findViewById<RadioButton>(selectedId)
                signupViewModel.gender = selectedButton.text.toString()

                (activity as? SignupActivity)?.nextStep()
            } else {
                Toast.makeText(requireContext(), "Please select a gender", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

