package com.elastic.web;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;

import com.elastic.web.util.ElasticSearchConnection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class ProcessTest {

	ElasticSearchConnection esc = new ElasticSearchConnection();
	
	private JsonArray gsonArr = new JsonArray();
	private Gson gson = new Gson();
	
	@Test
	public void testGetList() {
		
		/*SearchResponse response1 = esc.client.prepareSearch("elastic")
				.setTypes("board")
				.addSort("row_num", SortOrder.DESC)
				.setSize(1)
				.get();
		
		SearchHits rowNum = response1.getHits();
		for(int i=0;i<1;i++) {
		Map<String, Object> map = rowNum.getAt(0).getSourceAsMap();
		System.out.println(map);
		System.out.println(map.get("row_num"));
		}*/
		
		
		
		
		
		
		/*
		SearchResponse response = esc.client.prepareSearch("elastic")
				.setTypes("board")
		        //.setQuery(QueryBuilders.termQuery("*", "*"))                 // Query
		        //.setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))     // Filter
		        .setFrom(0)
		        .setSize(100).setExplain(true)
		        .get();
		
		*/

		/*SearchHit[] result = response.getHits().getHits();
		for(SearchHit hit : result) {
			String sourceAsString = hit.getSourceAsString();
            if (sourceAsString != null) {
            	
            }
		}*/
	}

}
