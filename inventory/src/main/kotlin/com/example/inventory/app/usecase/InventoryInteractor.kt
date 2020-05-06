package com.example.inventory.app.usecase

import com.example.inventory.domain.entity.ItemInventory

interface InventoryInteractor {
    fun findAll(): List<ItemInventory>
    fun deleteByID(id: String): Boolean
    fun updateById(id: String): Boolean
    fun create(itemInventory: ItemInventory): Int
}