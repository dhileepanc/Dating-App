package com.datingapp.app.ui.signup

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.datingapp.app.MainActivity
import com.datingapp.app.databinding.FragmentSignupStepAgeGroupBinding
import com.datingapp.app.di.viewmodel.SignupViewModel
import com.datingapp.app.utils.GetSet

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupStepAgeGroupFragment : Fragment() {

    private var _binding: FragmentSignupStepAgeGroupBinding? = null
    private val binding get() = _binding!!

    private val signupViewModel: SignupViewModel by activityViewModels()

    private val ageGroups = listOf("18-25", "26-35", "36-45", "46-60", "60+")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupStepAgeGroupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAgeGroupOptions()

        binding.btnFinish.setOnClickListener {
            val selectedId = binding.rgAgeGroup.checkedRadioButtonId
            if (selectedId != -1) {
                val selectedButton = binding.root.findViewById<RadioButton>(selectedId)
                signupViewModel.ageGroup = selectedButton.text.toString()

                signupViewModel.printAllData()

                GetSet.setLogged(requireContext(), true)

                val intent = Intent(requireContext(), MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            } else {
                Toast.makeText(requireContext(), "Please select an age group", Toast.LENGTH_SHORT).show()
            }
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
