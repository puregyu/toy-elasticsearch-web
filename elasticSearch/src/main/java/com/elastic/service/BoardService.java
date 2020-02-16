package com.elastic.service;

import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.elastic.entity.Board;

public interface BoardService {

	List<Board> getList();

	void write(Board board);

	Board read(String idx) throws ParseException;

	void edit(Board board) throws IOException;

	void delete(Board board) throws IOException;

	List<Board> search(String search, String option);

}
