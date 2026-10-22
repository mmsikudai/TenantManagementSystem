package com.example.tenantmanagementsystem

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

object TenantRepository {

    private const val PREFS_NAME = "tenant_storage"
    private const val TENANTS_KEY = "tenants"

    fun addTenant(context: Context, tenant: Tenant) {
        val tenants = getTenants(context).toMutableList()
        tenants.add(tenant)
        saveTenants(context, tenants)
    }

    fun getTenants(context: Context): List<Tenant> {
        val prefs = context.getSharedPreferences(
            PREFS_NAME,
            Context.MODE_PRIVATE
        )

        val json = prefs.getString(TENANTS_KEY, "[]") ?: "[]"
        val tenants = mutableListOf<Tenant>()

        try {
            val array = JSONArray(json)

            for (i in 0 until array.length()) {
                val item = array.getJSONObject(i)

                tenants.add(
                    Tenant(
                        name = item.getString("name"),
                        phone = item.getString("phone"),
                        rent = item.getString("rent")
                    )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return tenants
    }

    private fun saveTenants(
        context: Context,
        tenants: List<Tenant>
    ) {
        val array = JSONArray()

        tenants.forEach { tenant ->
            val item = JSONObject()
            item.put("name", tenant.name)
            item.put("phone", tenant.phone)
            item.put("rent", tenant.rent)
            array.put(item)
        }

        context.getSharedPreferences(
            PREFS_NAME,
            Context.MODE_PRIVATE
        ).edit()
            .putString(TENANTS_KEY, array.toString())
            .apply()
    }
}