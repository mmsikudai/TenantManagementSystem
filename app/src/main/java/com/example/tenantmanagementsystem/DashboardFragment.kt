package com.example.tenantmanagementsystem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import android.util.Log

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

        // Find the Dashboard buttons
        val addTenantButton =
            view.findViewById<Button>(R.id.addTenantButton)

        val viewTenantsButton =
            view.findViewById<Button>(R.id.viewTenantsButton)

        // Navigate to Add Tenant
        addTenantButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_dashboardFragment_to_addTenantFragment
            )
        }

        // Navigate to View Tenants
        viewTenantsButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_dashboardFragment_to_viewTenantsFragment
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TENANT_LIFECYCLE", "DashboardFragment: onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d("TENANT_LIFECYCLE", "DashboardFragment: onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("TENANT_LIFECYCLE", "DashboardFragment: onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("TENANT_LIFECYCLE", "DashboardFragment: onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("TENANT_LIFECYCLE", "DashboardFragment: onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("TENANT_LIFECYCLE", "DashboardFragment: onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TENANT_LIFECYCLE", "DashboardFragment: onDestroy")
    }

}
