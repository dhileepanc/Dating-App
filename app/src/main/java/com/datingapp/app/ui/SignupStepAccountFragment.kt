package com.datingapp.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.datingapp.app.databinding.FragmentSignupStepAccountBinding
import com.datingapp.app.di.viewmodel.SignupViewModel


class SignupStepAccountFragment : Fragment() {

    private var _binding: FragmentSignupStepAccountBinding? = null
    private val binding get() = _binding!!

    private val signupViewModel: SignupViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupStepAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNext.setOnClickListener {

            signupViewModel.name = binding.edtName.text.toString()
            signupViewModel.email = binding.edtEmail.text.toString()
            signupViewModel.location = binding.edtLocation.text.toString()
            
            (activity as? SignupActivity)?.nextStep()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
