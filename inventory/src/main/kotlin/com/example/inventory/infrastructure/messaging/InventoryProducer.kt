package com.example.inventory.infrastructure.messaging

import com.example.inventory.domain.entity.ItemInventory
import org.springframework.cloud.stream.messaging.Source
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component

@Component
class InventoryProducer(
        private val source: Source
) {
    fun publishInventoryCreateEvent(itemInventory: ItemInventory) {
        source.output().send(MessageBuilder.withPayload(itemInventory)
                .setHeader(KafkaHeaders.MESSAGE_KEY, itemInventory.id).build())
    }
}