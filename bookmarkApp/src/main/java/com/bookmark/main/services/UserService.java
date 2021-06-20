package com.bookmark.main.services;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.bookmark.main.models.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserService {

	private String path = "C:\\Users\\shnsa\\eclipse-workspace\\bookmarkApp\\src\\main\\resources\\BookmarkRoot\\user.txt";
	

	public boolean login(User user) {
		return findByUsernameAndPassword(user);
	}

	public boolean signup(User user) {
		return AddUser(user);
	}
	
	public boolean findByUsernameAndPassword(User user) {
		List<User> users = getAllUsers();
		for (User u : users) {
			if(u.getPassword().equals(user.getPassword()) && u.getUsername().equals(user.getUsername())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean AddUser(User user) {
			
			ObjectMapper mapper = new ObjectMapper();
			
			List<User> users;
			FileOutputStream outputStream;
			System.out.println(path);
			try {
				users = getAllUsers();
				outputStream = new FileOutputStream(path);
				
				if(users.size() == 0) {
					mapper.writeValue(outputStream, user);
				} else {
					users.add(user);
					mapper.writeValue(outputStream, users);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
	public List<User> getAllUsers() {
		List<User> users = null;
		ObjectMapper mapper = null;
		FileInputStream inputStream = null;
		BufferedReader bufferedReader = null;
		String jsonString= null;
		TypeReference<List<User>> typeReference= null;
		System.out.println(path);
		try {
			
			mapper = new ObjectMapper();
			users = new ArrayList<>();
			mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			
			inputStream = new FileInputStream(path);
			bufferedReader = new BufferedReader(new FileReader(path));
			
			
			typeReference = new TypeReference<List<User>>() {};
			jsonString = bufferedReader.readLine();
			System.out.println(jsonString);
			
			if(jsonString != null) {
				users = mapper.readValue(inputStream, typeReference);
			}
			
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return users;
	}
}
