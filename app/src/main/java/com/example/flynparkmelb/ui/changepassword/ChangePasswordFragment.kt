package com.example.flynparkmelb.ui.changepassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.flynparkmelb.databinding.FragmentChangepasswordBinding

class ChangePasswordFragment : Fragment() {

    private lateinit var changePasswordViewModel: ChangePasswordViewModel
    private var _binding: FragmentChangepasswordBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        changePasswordViewModel =
            ViewModelProvider(this).get(ChangePasswordViewModel::class.java)

        _binding = FragmentChangepasswordBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}