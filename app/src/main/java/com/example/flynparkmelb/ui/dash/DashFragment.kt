package com.example.flynparkmelb.ui.dash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.flynparkmelb.R
import com.example.flynparkmelb.databinding.FragmentDashBinding
import com.example.flynparkmelb.ui.searchparking.SearchParkingFragment


class DashFragment : Fragment() {

    private lateinit var dashViewModel: DashViewModel
    private var _binding: FragmentDashBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashViewModel =
            ViewModelProvider(this).get(DashViewModel::class.java)

        _binding = FragmentDashBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val ButtonsearchParking: Button = binding.appCompatButtonSearchParking
        ButtonsearchParking.setOnClickListener {

            val nextFrag = SearchParkingFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_content_main_dashboard, nextFrag)
                .addToBackStack(null)
                .commit()

            requireActivity().findNavController(R.id.nav_host_fragment_content_main_dashboard)
            (activity as AppCompatActivity?)!!.supportActionBar!!.setTitle("Search Parking")

        }

        val textView: TextView = binding.textViewTextTime
        dashViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}