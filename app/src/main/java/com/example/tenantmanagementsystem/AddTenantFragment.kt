package com.example.tenantmanagementsystem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class AddTenantFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.fragment_add_tenant,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nameEditText =
            view.findViewById<EditText>(R.id.tenantNameEditText)

        val phoneEditText =
            view.findViewById<EditText>(R.id.phoneEditText)

        val rentEditText =
            view.findViewById<EditText>(R.id.rentEditText)

        val saveButton =
            view.findViewById<Button>(R.id.saveButton)

        saveButton.setOnClickListener {

            val name = nameEditText.text.toString().trim()
            val phone = phoneEditText.text.toString().trim()
            val rent = rentEditText.text.toString().trim()

            if (name.isEmpty() || phone.isEmpty() || rent.isEmpty()) {

                Toast.makeText(
                    requireContext(),
                    "Please fill in all fields",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                val tenant = Tenant(name, phone, rent)

                // Save the tenant to the repository
                TenantRepository.addTenant(tenant)

                Toast.makeText(
                    requireContext(),
                    "Tenant saved successfully",
                    Toast.LENGTH_SHORT
                ).show()

                // Pass the tenant to the View Tenants screen
                val action =
                    AddTenantFragmentDirections
                        .actionAddTenantFragmentToViewTenantsFragment(tenant)

                findNavController().navigate(action)
            }
        }
    }
}
