package com.example.tenantmanagementsystem

object TenantRepository {

    private val tenants = mutableListOf<Tenant>()

    fun addTenant(tenant: Tenant) {
        tenants.add(tenant)
    }

    fun getTenants(): List<Tenant> {
        return tenants.toList()
    }
}