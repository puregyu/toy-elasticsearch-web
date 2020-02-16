package com.elastic.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.elastic.entity.Board;
import com.elastic.web.util.ElasticSearchProcessor;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	ElasticSearchProcessor esp;
	
	@Override
	public List<Board> getList() {
		
		List<Board> list = esp.getList();
		
		return list;
	}

	@Override
	public void write(Board board) {
		board.setIdx(UUID.randomUUID().toString());
		
		//날짜 포맷팅
		SimpleDateFormat sdf = 
				new SimpleDateFormat("yyyy-MM-dd",Locale.KOREAN);
		Date date = new Date();
		String d = sdf.format(date);
		
		board.setDate(d);;
		
		esp.insert(board);
		
	}

	@Override
	public Board read(String idx) throws ParseException {
		Board board = esp.getRead(idx);
		
		return board;
	}

	@Override
	public void edit(Board board) throws IOException {
		esp.update(board);
	}

	@Override
	public void delete(Board board) throws IOException {
		esp.delete(board);
	}

	@Override
	public List<Board> search(String search, String option) {
		
		List<Board> searchList = esp.SearchList(search, option);
		
		return searchList;
	}

}
