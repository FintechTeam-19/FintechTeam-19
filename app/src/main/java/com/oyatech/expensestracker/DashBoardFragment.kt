package com.oyatech.expensestracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.oyatech.expensestracker.databinding.FragmentDashBoardBinding

class DashBoardFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentDashBoardBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDashBoardBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.newTracker.setOnClickListener(this)
        binding.history.setOnClickListener(this)
        binding.statistic.setOnClickListener(this)
        binding.settings.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        if (view != null) {
            when (view.id) {
                R.id.new_tracker -> {
                    findNavController().navigate(R.id.action_dashBoardFragment_to_incomeFragment)
                }
                R.id.history ->{
                    findNavController().navigate(R.id.action_dashBoardFragment_to_historyFragment)
                }
                R.id.statistic ->        {
                    Toast.makeText(requireContext(),"To Statistic Page",Toast.LENGTH_SHORT).show()

                }
                R.id.settings ->        {
                    Toast.makeText(requireContext(),"To Settings Page",Toast.LENGTH_SHORT).show()

                }


            }
        }
    }

    private fun newMonthlyTracker() {
    }
    /**
     * TODO: WHY SAGE ARGUMENT ISN'T WORKING
     */

}