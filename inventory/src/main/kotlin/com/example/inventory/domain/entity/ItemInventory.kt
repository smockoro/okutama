package com.example.inventory.domain.entity

data class ItemInventory(
        val id: Int,
        val name: String,
        val inventory: Long,
        val active: Boolean,
        val description: String
)
