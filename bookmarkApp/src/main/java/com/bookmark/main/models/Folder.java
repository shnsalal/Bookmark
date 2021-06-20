package com.bookmark.main.models;

import java.util.Date;

public class Folder {
	private long id = new Date().getTime();
	private String name;
	private String type = "folder";
	private Date date = new Date();
	private String folderUrl;
	
	public Folder() {
		super();
	}

	public Folder(long id, String name, String type, Date date, String folderUrl) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.date = date;
		this.folderUrl = folderUrl;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFolderUrl() {
		return folderUrl;
	}

	public void setFolderUrl(String folderUrl) {
		this.folderUrl = folderUrl;
	}
	
	
}
