package com.bookmark.main.models;

import java.util.Date;

public class Bookmark {
	private long id = new Date().getTime();
	private String title;
	private String URL;
	private Date date = new Date();
//	private String tags;
//	private String description;
//	private String imageUrl;
	
	public Bookmark() {
		super();
	}
	
	
	public Bookmark(long id, String title, String uRL, Date date) {
		super();
		this.id = id;
		this.title = title;
		URL = uRL;
		this.date = date;
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
//	public String getTags() {
//		return tags;
//	}
//	public void setTags(String tags) {
//		this.tags = tags;
//	}
//	public String getDescription() {
//		return description;
//	}
//	public void setDescription(String description) {
//		this.description = description;
//	}
//	public String getImageUrl() {
//		return imageUrl;
//	}
//	public void setImageUrl(String imageUrl) {
//		this.imageUrl = imageUrl;
//	}
	
}
