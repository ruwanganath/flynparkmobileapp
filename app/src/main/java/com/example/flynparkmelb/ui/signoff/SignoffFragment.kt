package com.example.flynparkmelb.ui.signoff

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.flynparkmelb.LoginActivity
import com.example.flynparkmelb.MainDashboardActivity
import com.example.flynparkmelb.RegisterActivity
import com.example.flynparkmelb.databinding.FragmentSignoffBinding

class SignoffFragment : Fragment() {

    private lateinit var signoffViewModel: SignoffViewModel
    private var _binding: FragmentSignoffBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        signoffViewModel =
            ViewModelProvider(this).get(SignoffViewModel::class.java)

        _binding = FragmentSignoffBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val ButtonsignoffYes: Button = binding.appCompatButtonsignoffYes
        val ButtonsignoffCancel: Button = binding.appCompatButtonsignoffCancel
        ButtonsignoffYes.setOnClickListener(View.OnClickListener {
            val intentLogin = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intentLogin)
        })
        ButtonsignoffCancel.setOnClickListener(View.OnClickListener {
            val intentMainDashboard = Intent(requireContext(), MainDashboardActivity::class.java)
            startActivity(intentMainDashboard)
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}