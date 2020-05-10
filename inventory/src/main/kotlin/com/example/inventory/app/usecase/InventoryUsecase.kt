package com.example.inventory.app.usecase

import com.example.inventory.domain.entity.ItemInventory
import com.example.inventory.domain.repository.InventoryRepository
import com.example.inventory.infrastructure.messaging.InventoryProducer
import org.slf4j.Logger
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class InventoryUsecase(
        private val logger: Logger,
        private val inventoryRepository: InventoryRepository,
        private val inventoryProducer: InventoryProducer
): InventoryInteractor{

    override fun findAll(): List<ItemInventory> {
        return inventoryRepository.find()
    }

    override fun deleteByID(id: String): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateById(id: String): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = arrayOf(Exception::class))
    override fun create(itemInventory: ItemInventory): Int {
        val result = inventoryRepository.save(itemInventory)
        inventoryProducer.publishInventoryCreateEvent(itemInventory)
        return result
    }
}