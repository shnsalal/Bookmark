package com.bookmark.main.services;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.bookmark.main.models.Bookmark;
import com.bookmark.main.models.Folder;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class FolderService {
	
	private String root = "C:\\Users\\shnsa\\eclipse-workspace\\bookmarkApp\\src\\main\\resources\\BookmarkRoot";
	private String file = "C:\\Users\\shnsa\\eclipse-workspace\\bookmarkApp\\src\\main\\resources\\BookmarkRoot\\folder.txt";
	
	BookmarkService bookmarkService = new BookmarkService();
	
	public void saveFolderInformation(Folder folder) {
		ObjectMapper mapper = new ObjectMapper();
		
		List<Folder> folders;
		FileOutputStream outputStream;
		
		try {
			folders = getAllFolders();
			outputStream = new FileOutputStream(file);
			
			if(folders.size() == 0) {
				mapper.writeValue(outputStream, folder);
			} else {
				folders.add(folder);
				mapper.writeValue(outputStream, folders);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	public boolean createFolder(Folder folder) {
//		File path;
//		if(folder.getFolderUrl() == null) {
//			path = new File(root +"/"+ folder.getName());
//		} else {
//			path = new File(folder.getFolderUrl() +"/"+ folder.getName());
//		}
//		
//		boolean isCreated = path.mkdir();
//		if(isCreated) {
//			folder.setFolderUrl(path.toString());
//			saveFolderInformation(folder);
//		}
//		return isCreated;
//	}
	
	public boolean createFolder(Folder folder, String parentFolder) {
		File path;
		String folderPath = getFolderPath(parentFolder);
		if(folderPath == null) {
			path = new File(root +"/"+ folder.getName());
		} else {
			path = new File(folderPath +"/"+ folder.getName());
		}
		
		boolean isCreated = path.mkdir();
		if(isCreated) {
			folder.setFolderUrl(path.toString());
			saveFolderInformation(folder);
		}
		return isCreated;
	}
	
	
	private String getFolderPath(String parentFolder) {
		List<Folder> folders = getAllFolders();
		for (Folder folder : folders) {
			if(folder.getName().equals(parentFolder)) {
				return folder.getFolderUrl();
			}
		}
		return null;
	}

	
	public List<Folder> getAllFolders() {
		
		List<Folder> folders = null;
		ObjectMapper mapper = null;
		FileInputStream inputStream = null;
		BufferedReader bufferedReader = null;
		String jsonString= null;
		TypeReference<List<Folder>> typeReference= null;
		
		try {
			folders = new ArrayList<>();
			mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			
			inputStream = new FileInputStream(file);
			bufferedReader = new BufferedReader(new FileReader(file));
			
			
			typeReference = new TypeReference<List<Folder>>() {};
			jsonString = bufferedReader.readLine();
			System.out.println(jsonString);
			
			if(jsonString != null) {
				folders = mapper.readValue(inputStream, typeReference);
			}
			
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return folders;
	}

	public List<Bookmark> searchPathandShowAllBookmarks(String folderName) {
		List<Folder> folders = getAllFolders();
		List<Bookmark> bookmarks = new ArrayList<>();
		for (Folder folder : folders) {
			if(folder.getName().equals(folderName)) {
				bookmarks =  bookmarkService.getAllBookmarks(folder.getFolderUrl());
			}
		}
		return bookmarks;
	}

	public boolean selectFolder(String folderName, Bookmark bookmark) {
		List<Folder> folders = getAllFolders();
		for (Folder folder : folders) {
			if(folder.getName().equals(folderName)) {
				bookmarkService.addBookmarks(folder.getFolderUrl(), bookmark);
				return true;
			}
		}
		return false;
	}
	
	public boolean deleteFolder(String folderName) {
		File currentFile;
		List<Folder> folders = getAllFolders();
		for (Folder folder : folders) {
			if(folderName.equals("BookmarkRoot")) {
				return false;
			}
			if(folder.getName().equals(folderName)) {
				currentFile = new File(folder.getFolderUrl());
				try {
					FileUtils.deleteDirectory(currentFile);
					currentFile.delete();
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			    return !currentFile.exists();
			}
		}
		return false;
	}
}
