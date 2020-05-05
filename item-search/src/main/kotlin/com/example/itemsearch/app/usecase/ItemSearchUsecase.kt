package com.example.itemsearch.app.usecase

import com.example.itemsearch.domain.entity.Item
import com.example.itemsearch.domain.repository.ItemSearchRepository
import org.springframework.stereotype.Service

@Service
class ItemSearchUsecase(
        private val itemSearchRepository: ItemSearchRepository
): ItemSearchInteractor{

    override fun searchItem(): List<Item> {
        return itemSearchRepository.searchItem()
    }

    override fun searchItemById(id: String): List<Item> {
        return itemSearchRepository.searchItemById(id)
    }
}