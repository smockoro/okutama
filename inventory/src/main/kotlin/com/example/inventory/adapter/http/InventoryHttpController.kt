package com.example.inventory.adapter.http

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class InventoryHttpController {

    @GetMapping("/hello")
    fun hello(): String {
        return "Hello"
    }
}