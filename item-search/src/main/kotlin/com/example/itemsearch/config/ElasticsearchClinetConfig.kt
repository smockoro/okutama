package com.example.itemsearch.config

import org.apache.http.HttpHost
import org.elasticsearch.client.RestClient
import org.elasticsearch.client.RestHighLevelClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope

@Configuration
class ElasticsearchClinetConfig {
    @Value("\${spring.elasticsearch.protocol}")
    private val elasticsearchProtcol = "http"

    @Value("\${spring.elasticsearch.host}")
    private val elasticsearchHost = "localhost"

    @Value("\${spring.elasticsearch.port}")
    private val elasticsearchPort = 9200

    @Bean
    @Scope(value= ConfigurableBeanFactory.SCOPE_SINGLETON)
    fun elasticsearchClient(): RestHighLevelClient {
        val elasticsearchClient = RestHighLevelClient(RestClient.builder(
                HttpHost(elasticsearchHost, elasticsearchPort, elasticsearchProtcol)
        ))

        return elasticsearchClient
    }

}