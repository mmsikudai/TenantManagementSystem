package com.example.tenantmanagementsystem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class ViewTenantsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.fragment_view_tenants,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tenantsTextView =
            view.findViewById<TextView>(R.id.tenantsTextView)

        val savedTenants = TenantRepository.getTenants()

        val receivedTenant = arguments?.let {
            ViewTenantsFragmentArgs.fromBundle(it).newTenant
        }

        val tenants = if (savedTenants.isNotEmpty()) {
            savedTenants
        } else if (receivedTenant != null) {
            listOf(receivedTenant)
        } else {
            emptyList()
        }

        tenantsTextView.text = if (tenants.isEmpty()) {
            "No tenants have been added yet."
        } else {
            tenants.joinToString(separator = "\n\n") { tenant ->
                "Name: ${tenant.name}\n" +
                        "Phone: ${tenant.phone}\n" +
                        "Monthly Rent: KSh ${tenant.rent}"
            }
        }
    }
}
