package com.bookmark.main.services;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import com.bookmark.main.models.Bookmark;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class BookmarkService {
	
	private String path = "C:\\Users\\shnsa\\eclipse-workspace\\bookmarkApp\\src\\main\\resources\\BookmarkRoot";
	
	public void addBookmarks(Bookmark bookmark) {
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<Bookmark> bookmarks;
		FileOutputStream outputStream;
		path = path + "\\" + "bookmark.txt";
		System.out.println(path);
		try {
			bookmarks = getAllBookmarks();
			outputStream = new FileOutputStream(path);
			
			if(bookmarks.size() == 0) {
				mapper.writeValue(outputStream, bookmark);
			} else {
				bookmarks.add(bookmark);
				mapper.writeValue(outputStream, bookmarks);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addBookmarks(String p,  Bookmark bookmark) {
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<Bookmark> bookmarks;
		FileOutputStream outputStream;
		path = p + "\\" + "bookmark.txt";
		System.out.println(path);
		try {
			bookmarks = getAllBookmarks();
			outputStream = new FileOutputStream(path);
			
			if(bookmarks.size() == 0) {
				mapper.writeValue(outputStream, bookmark);
			} else {
				bookmarks.add(bookmark);
				mapper.writeValue(outputStream, bookmarks);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public List<Bookmark> getAllBookmarks() {
		
		List<Bookmark> bookmarks = null;
		ObjectMapper mapper = null;
		FileInputStream inputStream = null;
		BufferedReader bufferedReader = null;
		String jsonString= null;
		TypeReference<List<Bookmark>> typeReference= null;
		String filepath = path + "\\" + "bookmark.txt";
		System.out.println(filepath);
		try {
			bookmarks = new ArrayList<>();
			mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			
			inputStream = new FileInputStream(filepath);
			bufferedReader = new BufferedReader(new FileReader(filepath));
			
			
			typeReference = new TypeReference<List<Bookmark>>() {};
			jsonString = bufferedReader.readLine();
			System.out.println(jsonString);
			
			if(jsonString != null) {
				bookmarks = mapper.readValue(inputStream, typeReference);
			}
			
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return bookmarks;
	}
	
	
	public List<Bookmark> getAllBookmarks(String p) {
			
			List<Bookmark> bookmarks = null;
			ObjectMapper mapper = null;
			FileInputStream inputStream = null;
			BufferedReader bufferedReader = null;
			String jsonString= null;
			TypeReference<List<Bookmark>> typeReference= null;
			String path = p + "\\" + "bookmark.txt";
			System.out.println(path);
			try {
				bookmarks = new ArrayList<>();
				mapper = new ObjectMapper();
				mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
				
				inputStream = new FileInputStream(path);
				bufferedReader = new BufferedReader(new FileReader(path));
				
				
				typeReference = new TypeReference<List<Bookmark>>() {};
				jsonString = bufferedReader.readLine();
				System.out.println(jsonString);
				
				if(jsonString != null) {
					bookmarks = mapper.readValue(inputStream, typeReference);
				}
				
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return bookmarks;
		}
}
