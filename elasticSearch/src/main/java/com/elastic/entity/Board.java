package com.elastic.entity;

import java.util.Date;


public class Board {
	
	//field
	private String rowNum;
	private String idx;
	private String date;
	private String subject;
	private String content;
	private boolean isDelete;
	
	public Board() {
		// TODO Auto-generated constructor stub
	}
	
	public Board(String rowNum, String idx, String date, String subject, String content, boolean isDelete) {
		this.rowNum = rowNum;
		this.idx = idx;
		this.date = date;
		this.subject = subject;
		this.content = content;
		this.isDelete = isDelete;
	}

	public String getRowNum() {
		return rowNum;
	}

	public void setRowNum(String string) {
		this.rowNum = string;
	}

	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String d) {
		this.date = d;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	@Override
	public String toString() {
		return "Board [rowNum=" + rowNum + ", idx=" + idx + ", date=" + date + ", subject=" + subject + ", content="
				+ content + ", isDelete=" + isDelete + "]";
	}
}
