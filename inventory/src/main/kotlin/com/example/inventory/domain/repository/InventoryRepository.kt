package com.example.inventory.domain.repository

import com.example.inventory.domain.entity.ItemInventory

interface InventoryRepository {
    fun find(): List<ItemInventory>
    fun deleteById(id: Int): Boolean
    fun updateById(id: Int): Boolean
    fun save(itemInventory: ItemInventory): Int
}