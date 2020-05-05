package com.example.itemsearch.adapter.http

import com.example.itemsearch.app.usecase.ItemSearchInteractor
import com.example.itemsearch.domain.entity.Item
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ItemSearchHttpController(
        private val itemSearchInteractor: ItemSearchInteractor
) {

    @PostMapping("/item")
    fun searchItem(): ResponseEntity<List<Item>> {
        val results = itemSearchInteractor.searchItem()
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(results)
    }

    @PostMapping("/item/{id}")
    fun searchItemById(@PathVariable id: String): ResponseEntity<List<Item>> {
        val results = itemSearchInteractor.searchItemById(id)
        return  ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(results)
    }
}