package com.example.flynparkmelb.ui.deleteaccount

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.flynparkmelb.LoginActivity
import com.example.flynparkmelb.MainDashboardActivity
import com.example.flynparkmelb.databinding.FragmentDeleteaccountBinding

class DeleteAccountFragment: Fragment() {

    private lateinit var deleteAccountViewModel: DeleteAccountViewModel
    private var _binding: FragmentDeleteaccountBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        deleteAccountViewModel =
            ViewModelProvider(this).get(DeleteAccountViewModel::class.java)

        _binding = FragmentDeleteaccountBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val ButtondeleteaccountYes: Button = binding.appCompatButtondeleteaccountYes
        val ButtondeleteaccountCancel: Button = binding.appCompatButtondeleteaccountCancel
        ButtondeleteaccountYes.setOnClickListener(View.OnClickListener {
            val intentLogin = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intentLogin)
        })
        ButtondeleteaccountCancel.setOnClickListener(View.OnClickListener {
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