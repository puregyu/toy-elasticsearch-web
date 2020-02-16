package com.elastic.web;


import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.lucene.search.TermQuery;
import org.elasticsearch.action.admin.cluster.state.ClusterStateResponse;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Setting;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.elastic.entity.Board;
import com.elastic.web.util.ElasticSearchProcessor;
import com.google.gson.JsonObject;


public class connectionTest {
	
	
	
	public static void main(String args[]) throws UnknownHostException {
		//Settings setting = Settings.builder().put("cluster.name","my-application").build();
		
		//String host = "172.16.100.128";
		
		//TransportClient client = new PreBuiltTransportClient (Settings.EMPTY)
		//		.addTransportAddress(new TransportAddress(InetAddress.getByName(host), 9200));
		
		
		/*Settings settings = Settings.builder()
	            .put("network.host", "0.0.0.0")
	            .build();
	    TransportClient client = new PreBuiltTransportClient(settings)
	    		.addTransportAddress(new TransportAddress(InetAddress.getByName("172.16.100.128"), 9300));
		*/
	    TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
	    		.addTransportAddress(new TransportAddress(new InetSocketAddress("172.16.100.128", 9300)));
		 
		
		
		//Settings setting = Settings.builder().put("network.host","0.0.0.0").build();
	    //Client client = new PreBuiltTransportClient(setting);
		
		//.addTransportAddress (new TransportAddress (InetAddress.getByName("172.16.100.128_search"), 9200));
		//TransportClient client = new PreBuiltTransportClient (Settings.EMPTY)
		//  .addTransportAddress (new TransportAddress (InetAddress.getByName("172.16.100.128"), 9200));
		
		
		/*Settings settings = Settings.builder()
		        .put("client.transport.sniff", true).build();
		TransportClient client = new PreBuiltTransportClient(settings);*/
		
		//read
		//GetResponse response = client.prepareGet("elastic","board","1").get();
		//System.out.println(response);
		
		//insert
		/*Map<String, Object> json = new HashMap<String, Object>();
		json.put("row_num",3);
		json.put("idx", UUID.randomUUID().toString());
		json.put("subject","테스트제목3");
		json.put("content","테스트내용3");
		json.put("date", "1212-12-12");
		json.put("is_deleted", false);
		
		IndexResponse response2 = client.prepareIndex("elastic", "board")
		        .setSource(json)
		        .get();*/

		/*for (MultiGetItemResponse itemResponse : multiGetItemResponses) { 
		    GetResponse response2 = itemResponse.getResponse();
		    if (response.isExists()) {                      
		        String json = response.getSourceAsString();
		        System.out.println(json);
		    }
		}*/
		 
		//delete
		/*DeleteResponse response3 = client.prepareDelete("elastic","board","Y8KRqGYBfOv8nqHJ2Bv2").get();
		
		MultiGetResponse multiGetItemResponses = client.prepareMultiGet()
				.add("elastic", "board","_search?pretty=true&q=*:*")
			    .get();
		 
		for (MultiGetItemResponse itemResponse : multiGetItemResponses) { 
		    GetResponse response1 = itemResponse.getResponse();
		    if (response1.isExists()) {                      
		        String json = response1.getSourceAsString();
		        System.out.println(json);
		    }
		}*/
		
		
		/*SearchResponse response = client.prepareSearch("elastic")
				.setTypes("board")
		        //.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
		        //.setQuery(QueryBuilders.termQuery("*", "*"))                 // Query
		        //.setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))     // Filter
		        .setFrom(0).setSize(60).setExplain(true)
		        .get();
		System.out.println(response);*/
	    
	    GetResponse response = client.prepareGet("elastic", "board", "2cc26c82-ab30-44ea-9a1a-7af18a0f17f8").get();

	    System.out.println(response.getSourceAsString());
	    System.out.println(response.getSource());
	   /* System.out.println(response.getSource());
	    System.out.println(response.getSourceAsString());
	    Map<String, Object> json = new HashMap<String, Object>();
		json = response.getSourceAsMap();
		System.out.println(json);*/
	}
}
