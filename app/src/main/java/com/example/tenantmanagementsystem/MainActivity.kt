
package com.example.tenantmanagementsystem

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tenantmanagementsystem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Save tenant details
        binding.saveButton.setOnClickListener {
            val name = binding.tenantNameEditText.text.toString().trim()
            val phone = binding.phoneEditText.text.toString().trim()
            val rent = binding.rentEditText.text.toString().trim()

            if (name.isEmpty() || phone.isEmpty() || rent.isEmpty()) {
                Toast.makeText(
                    this,
                    "Please fill in all tenant details",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val tenant = Tenant(name, phone, rent)
                binding.tenant = tenant

                Toast.makeText(
                    this,
                    "Tenant details saved successfully",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // Open the phone dialer
        binding.callTenantButton.setOnClickListener {
            val phone = binding.phoneEditText.text.toString().trim()

            if (phone.isEmpty()) {
                Toast.makeText(
                    this,
                    "Please enter the tenant's phone number",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val intent = Intent(
                    Intent.ACTION_DIAL,
                    Uri.parse("tel:$phone")
                )

                startActivity(intent)
            }
        }
    }
}