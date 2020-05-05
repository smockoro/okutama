package com.example.itemsearch.app.usecase

import com.example.itemsearch.domain.entity.Item

interface ItemSearchInteractor {
    fun searchItem(): List<Item>
    fun searchItemById(id: String): List<Item>
}