package com.example.itemsearch.domain.entity

data class Item(
        var itemId: String,
        var name: String,
        var inventory: Long,
        var active: Boolean,
        var description: String
)
