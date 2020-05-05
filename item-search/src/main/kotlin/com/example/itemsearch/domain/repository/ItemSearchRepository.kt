package com.example.itemsearch.domain.repository

import com.example.itemsearch.domain.entity.Item

interface ItemSearchRepository {
    fun searchItem(): List<Item>
    fun searchItemById(id: String): List<Item>
}