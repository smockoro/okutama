package com.example.itemsearch.infrastructure.repository

import com.example.itemsearch.config.ElasticsearchClinetConfig
import com.example.itemsearch.domain.entity.Item
import com.example.itemsearch.domain.repository.ItemSearchRepository
import org.apache.http.client.methods.RequestBuilder
import org.elasticsearch.action.search.SearchRequest
import org.elasticsearch.action.search.SearchRequestBuilder
import org.elasticsearch.action.search.SearchResponse
import org.elasticsearch.client.RequestOptions
import org.elasticsearch.index.query.QueryBuilder
import org.elasticsearch.index.query.QueryBuilders
import org.elasticsearch.search.builder.SearchSourceBuilder
import org.springframework.stereotype.Repository
import java.io.Closeable

@Repository
class ItemSearchElasticsearchRepository(
        private val elasticsearchClinetConfig: ElasticsearchClinetConfig
): ItemSearchRepository,Closeable {

    private val elasticsearchClinet = elasticsearchClinetConfig.elasticsearchClient()
    private val ELATICSEARCH_INDEX = "item"

    override fun searchItem(): List<Item> {
        val searchSourceBuilder = SearchSourceBuilder.searchSource()
                .from(0)
                .size(10)
        val searchRequest = SearchRequest().indices(ELATICSEARCH_INDEX)
                .source(searchSourceBuilder)
        val results = elasticsearchClinet.search(searchRequest, RequestOptions.DEFAULT)
                .hits

        val items = mutableListOf<Item>()
        results.forEach{ elem ->
            items.add(Item(
                    elem.sourceAsMap.get("item_id") as String,
                    elem.sourceAsMap.get("name") as String,
                    elem.sourceAsMap.get("inventory").toString().toLong(),
                    elem.sourceAsMap.get("active") as Boolean,
                    elem.sourceAsMap.get("description") as String
            ))
        }
        return items.toList()
    }

    override fun searchItemById(id: String): List<Item> {
        val searchSourceBuilder = SearchSourceBuilder.searchSource()
                .from(0)
                .size(10)
                .query(QueryBuilders.termQuery("item_id", id))
        val searchRequest = SearchRequest().indices(ELATICSEARCH_INDEX)
                .source(searchSourceBuilder)
        val results = elasticsearchClinet.search(searchRequest, RequestOptions.DEFAULT)
                .hits

        val items = mutableListOf<Item>()
        results.forEach{ elem ->
            items.add(Item(
                    elem.sourceAsMap.get("item_id") as String,
                    elem.sourceAsMap.get("name") as String,
                    elem.sourceAsMap.get("inventory").toString().toLong(),
                    elem.sourceAsMap.get("active") as Boolean,
                    elem.sourceAsMap.get("description") as String
            ))
        }
        return items.toList()
    }

    override fun close() {
        elasticsearchClinet.close()
    }
}

