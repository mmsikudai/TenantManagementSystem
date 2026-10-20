package com.example.tenantmanagementsystem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.fragment_dashboard,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val addTenantButton = view.findViewById<Button>(R.id.addTenantButton)
        val viewTenantsButton = view.findViewById<Button>(R.id.viewTenantsButton)

        addTenantButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_dashboardFragment_to_addTenantFragment
            )
        }

        viewTenantsButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_dashboardFragment_to_viewTenantsFragment
            )
        }
    }
}
