package com.example.inventory.adapter.http

import com.example.inventory.app.usecase.InventoryInteractor
import com.example.inventory.domain.entity.ItemInventory
import org.slf4j.Logger
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class InventoryHttpController(
        private val logger: Logger,
        private val inventoryInteractor: InventoryInteractor
) {

    @GetMapping("/hello")
    fun hello(): String {
        return "Hello"
    }

    @GetMapping("/inventory/item")
    fun findAllItemInventory(): ResponseEntity<List<ItemInventory>> {
        val itemInventory = inventoryInteractor.findAll()
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(itemInventory)
    }

    @PutMapping("/inventory/item")
    fun createItemInventory(@RequestBody itemInventory: ItemInventory): ResponseEntity<Int> {
        var result = 0
        try {
            result = inventoryInteractor.create(itemInventory)
        } catch (e: Exception) {
            logger.error("transaction is rollbacked", e)
            return ResponseEntity.badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(result)
        }
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(result)
    }
}