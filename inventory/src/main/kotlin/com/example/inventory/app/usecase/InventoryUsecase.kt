package com.example.inventory.app.usecase

import com.example.inventory.domain.entity.ItemInventory
import com.example.inventory.domain.repository.InventoryRepository
import org.springframework.stereotype.Service

@Service
class InventoryUsecase(
        private val invetoryRepository: InventoryRepository
): InventoryInteractor{

    override fun findAll(): List<ItemInventory> {
        return invetoryRepository.find()
    }

    override fun deleteByID(id: String): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateById(id: String): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun create(itemInventory: ItemInventory): Int {
        return invetoryRepository.save(itemInventory)
    }
}