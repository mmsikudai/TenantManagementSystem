package com.example.tenantmanagementsystem

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


class AddTenantFragment : Fragment() {

    private val tag = "TENANT_LIFECYCLE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "AddTenantFragment: onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(tag, "AddTenantFragment: onCreateView")

        return inflater.inflate(
            R.layout.fragment_add_tenant,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(tag, "AddTenantFragment: onViewCreated")

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
                return@setOnClickListener
            }

            val digitsOnly = phone.filter { it.isDigit() }

            if (digitsOnly.length < 10) {
                phoneEditText.error = "Enter a valid phone number"
                phoneEditText.requestFocus()
                return@setOnClickListener
            }

            phoneEditText.error = null

            val tenant = Tenant(name, phone, rent)
            TenantRepository.addTenant(requireContext(), tenant)

            Toast.makeText(
                requireContext(),
                "Tenant saved successfully",
                Toast.LENGTH_SHORT
            ).show()

            val action =
                AddTenantFragmentDirections
                    .actionAddTenantFragmentToViewTenantsFragment(tenant)

            findNavController().navigate(action)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "AddTenantFragment: onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "AddTenantFragment: onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "AddTenantFragment: onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag, "AddTenantFragment: onStop")
    }

    override fun onDestroyView() {
        Log.d(tag, "AddTenantFragment: onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d(tag, "AddTenantFragment: onDestroy")
        super.onDestroy()
    }
}