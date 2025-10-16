package com.datingapp.app.ui.signup

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.datingapp.app.databinding.FragmentSignupDobBinding
import com.datingapp.app.di.viewmodel.SignupViewModel
import com.datingapp.app.ui.SignupActivity

import java.text.SimpleDateFormat
import java.util.*
import kotlin.getValue

class SignupStepDOBFragment : Fragment() {

    private var _binding: FragmentSignupDobBinding? = null
    private val binding get() = _binding!!

    private val calendar = Calendar.getInstance()

    private val signupViewModel: SignupViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupDobBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvDob.setOnClickListener {
            showDatePicker()
        }

        binding.btnNext.setOnClickListener {
            // Pass DOB to parent activity or next fragment
            val dob = binding.tvDob.text.toString()
            if (dob.isNotEmpty() && dob != "Tap to select your DOB") {
                signupViewModel.dob = dob
                (activity as? SignupActivity)?.nextStep()
            } else {
                binding.tvDob.error = "Please select your date of birth"
            }
        }
    }

    private fun showDatePicker() {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(requireContext(), { _, y, m, d ->
            calendar.set(y, m, d)
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            binding.tvDob.text = sdf.format(calendar.time)
        }, year, month, day)

        // Optional: set max date (e.g., user must be 18+)
        datePicker.datePicker.maxDate = Calendar.getInstance().timeInMillis - (18L * 365 * 24 * 60 * 60 * 1000)
        datePicker.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
