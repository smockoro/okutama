package com.example.inventory.infrastructure.repository

import com.example.inventory.domain.entity.ItemInventory
import com.example.inventory.domain.repository.InventoryRepository
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import inventory.generated.jooq.Tables.ITEM_INVENTORY
import inventory.generated.jooq.tables.records.ItemInventoryRecord

@Repository
class InventoryMysqlRepository(
        private val context: DSLContext
): InventoryRepository {
    override fun find(): List<ItemInventory> {
        return context.select()
                .from(ITEM_INVENTORY)
                .fetch()
                .map { toItemInventoryEntity(it as ItemInventoryRecord) }
    }

    private fun toItemInventoryEntity(r: ItemInventoryRecord): ItemInventory {
        var active = true
        if (r.active.toInt().equals(0)) {
            active = false
        }
        return ItemInventory(
                r.id as Int,
                r.name,
                r.inventory.toString().toLong(),
                active,
                r.description
        )
    }

    override fun deleteById(id: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateById(id: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun save(itemInventory: ItemInventory): Int {
        return context.insertInto(ITEM_INVENTORY)
                .set(ItemInventoryRecord(
                        itemInventory.id,
                        itemInventory.name,
                        itemInventory.inventory.toString(),
                        1,
                        itemInventory.description))
                .execute()
    }

}