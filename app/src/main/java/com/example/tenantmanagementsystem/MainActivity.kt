package com.example.tenantmanagementsystem

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.tenantmanagementsystem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("TENANT_LIFECYCLE", "MainActivity: onCreate")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Connect the toolbar
        setSupportActionBar(binding.toolbar)

        // Get the navigation controller
        val navHostFragment =
            supportFragmentManager.findFragmentById(
                R.id.nav_host_fragment
            ) as NavHostFragment

        navController = navHostFragment.navController

        // Configure the navigation drawer
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.dashboardFragment,
                R.id.addTenantFragment,
                R.id.viewTenantsFragment
            ),
            binding.drawerLayout
        )

        // Connect the toolbar to navigation
        setupActionBarWithNavController(
            navController,
            appBarConfiguration
        )

        // Handle drawer selections, including Logout
        binding.navView.setNavigationItemSelectedListener { item ->

            when (item.itemId) {
                R.id.action_logout -> {
                    logout()
                    true
                }

                else -> {
                    val handled =
                        NavigationUI.onNavDestinationSelected(
                            item,
                            navController
                        )

                    if (handled) {
                        binding.drawerLayout.closeDrawer(
                            GravityCompat.START
                        )
                    }

                    handled
                }
            }
        }

        // Display the logged-in user's email
        val email = intent.getStringExtra("EMAIL") ?: "Signed in"

        val headerView = binding.navView.getHeaderView(0)

        val emailTextView = headerView.findViewById<TextView>(
            R.id.headerEmailTextView
        )

        emailTextView.text = email
    }

    // Logout and return to LoginActivity
    private fun logout() {
        val logoutIntent = Intent(this, LoginActivity::class.java)

        logoutIntent.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK

        startActivity(logoutIntent)
    }

    // Display the three-dot options menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    // Handle options menu navigation and Logout
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.action_logout) {
            logout()
            return true
        }

        return NavigationUI.onNavDestinationSelected(
            item,
            navController
        ) || super.onOptionsItemSelected(item)
    }

    // Handle the toolbar Up button
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) ||
                super.onSupportNavigateUp()
    }

    // Activity lifecycle logging
    override fun onStart() {
        super.onStart()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onDestroy")
    }
}