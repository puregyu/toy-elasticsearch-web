package com.elastic.web.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;
import static org.elasticsearch.common.xcontent.XContentFactory.*;
import com.elastic.entity.Board;
import com.fasterxml.jackson.core.sym.Name;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

 
@Repository
public class ElasticSearchProcessor {

	private ElasticSearchConnection esc = new ElasticSearchConnection();
	private Gson gson = new Gson();
	
	public List<Board> getList() {

		List<Board> list = new ArrayList<>();
		
		SearchResponse response = esc.client.prepareSearch("elastic")
				.setTypes("board")
				.addSort("row_num", SortOrder.DESC)
		        .setSize(100).setExplain(true)
		        .setQuery(QueryBuilders.matchQuery("is_deleted", false))
		        .get();
		 
		SearchHit[] hits = response.getHits().getHits();
		
		for(int i=0;i<hits.length;i++) {
			String a = hits[i].getSourceAsString();
			String ch = a.replace("row_num", "rowNum");
			Board board = gson.fromJson(ch, Board.class);
			list.add(board);
		}
		
		return list;
	}

	public void insert(Board board) {
		
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("idx", board.getIdx());
		json.put("subject", board.getSubject());
		json.put("content", board.getContent());
		json.put("date", board.getDate());
		json.put("is_deleted", board.isDelete());
		
		//최신 글 row_num 가져오기
		SearchResponse response1 = esc.client.prepareSearch("elastic")
								.setTypes("board")
								.addSort("row_num", SortOrder.DESC)
								.setSize(1)
								.get();
		
		SearchHits rowNum = response1.getHits();
		
		try {
			Map<String, Object> map = rowNum.getAt(0).getSourceAsMap();
			int i = (int) map.get("row_num");
			i++;
			json.put("row_num", i);
		} catch (Exception e) {
			json.put("row_num", 1);
		}
		
		IndexResponse response = esc.client.prepareIndex("elastic", "board")
				.setId(board.getIdx())
		        .setSource(json)
		        .get();
		
	}

	public Board getRead(String idx) throws ParseException {
		
		GetResponse response = esc.client.prepareGet("elastic", "board", idx).get();
		String read = response.getSourceAsString();
		
		//1.parser 이용
		//JSONParser parser = new JSONParser();
		//JSONObject jobj = (JSONObject) parser.parse(read);

		//2.gson 이용
		String ch = read.replace("row_num", "rowNum");
		Board board = gson.fromJson(ch,Board.class);
		
		return board;
	}

	public void update(Board board) throws IOException {
		
		esc.client.prepareUpdate("elastic", "board", board.getIdx())
		        .setDoc(jsonBuilder()               
		            .startObject()
		            .field("subject", board.getSubject())
		            .field("content", board.getContent())
		            .endObject())
		        .get();
		
	}

	public void delete(Board board) throws IOException {
		
		esc.client.prepareUpdate("elastic", "board", board.getIdx())
        .setDoc(jsonBuilder()               
            .startObject()
            .field("is_deleted", true)
            .endObject())
        .get();
		
	}

	public List<Board> SearchList(String search, String option) {
		
		List<Board> list = new ArrayList<>();
		
		switch(option) {
		
		case "subject": 
				SearchResponse response = esc.client.prepareSearch("elastic")
				.setTypes("board")
				.addSort("row_num", SortOrder.DESC)
				.setSize(100).setExplain(true)
			    .setQuery(QueryBuilders.wildcardQuery("subject", "*"+search+"*"))
				.get();
				
				SearchHit[] hits = response.getHits().getHits();
				
				for(int i=0;i<hits.length;i++) {
					String a = hits[i].getSourceAsString();
					String ch = a.replace("row_num", "rowNum");
					Board board = gson.fromJson(ch, Board.class);
					list.add(board);
				}
				break;
				
		case "content":
				response = esc.client.prepareSearch("elastic")
				.setTypes("board")
				.addSort("row_num", SortOrder.DESC)
				.setSize(100).setExplain(true)
			    .setQuery(QueryBuilders.wildcardQuery("content", "*"+search+"*"))
				.get();
				
				SearchHit[] hits2 = response.getHits().getHits();
				
				for(int i=0;i<hits2.length;i++) {
					String a = hits2[i].getSourceAsString();
					String ch = a.replace("row_num", "rowNum");
					Board board = gson.fromJson(ch, Board.class);
					list.add(board);
				}
				break;
				
		case "subject,content": 
				response = esc.client.prepareSearch("elastic")
				.setTypes("board")
				.addSort("row_num", SortOrder.DESC)
				.setSize(100).setExplain(true)
			    .setQuery(QueryBuilders.boolQuery()
			    	.should(QueryBuilders.wildcardQuery("subject", "*"+search+"*"))
			    	.should(QueryBuilders.wildcardQuery("content", "*"+search+"*")))
			    .get();
				
				SearchHit[] hits3 = response.getHits().getHits();
				
				for(int i=0;i<hits3.length;i++) {
					String a = hits3[i].getSourceAsString();
					String ch = a.replace("row_num", "rowNum");
					Board board = gson.fromJson(ch, Board.class);
					list.add(board);
				}
				break;
				
		default:
				 response = esc.client.prepareSearch("elastic")
				.setTypes("board")
				.addSort("row_num", SortOrder.DESC)
		        .setSize(100).setExplain(true)
		        .setQuery(QueryBuilders.matchQuery("is_deleted", false))
		        .get();
				
				 SearchHit[] hits4 = response.getHits().getHits();
					
					for(int i=0;i<hits4.length;i++) {
						String a = hits4[i].getSourceAsString();
						String ch = a.replace("row_num", "rowNum");
						Board board = gson.fromJson(ch, Board.class);
						list.add(board);
					}
				break;
				
		}
		return list;
	}
}
