package com.example.tenantmanagementsystem

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
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

        val callButton =
            view.findViewById<Button>(R.id.callButton)

        // Load tenants from persistent storage
        val savedTenants = TenantRepository.getTenants(requireContext())

        // Receive the newly added tenant through Safe Args
        val receivedTenant =
            arguments?.let {
                ViewTenantsFragmentArgs.fromBundle(it).newTenant
            }

        // Avoid displaying the newly added tenant twice
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

        callButton.setOnClickListener {
            if (tenants.isNotEmpty()) {
                val phoneNumber = tenants.first().phone

                val intent = Intent(
                    Intent.ACTION_DIAL,
                    Uri.parse("tel:$phoneNumber")
                )

                startActivity(intent)
            } else {
                Toast.makeText(
                    requireContext(),
                    "No tenant available to call.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}