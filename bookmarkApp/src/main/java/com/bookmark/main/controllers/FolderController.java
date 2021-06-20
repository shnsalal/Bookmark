package com.bookmark.main.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmark.main.models.Bookmark;
import com.bookmark.main.models.Folder;
import com.bookmark.main.services.FolderService;

@RestController
@RequestMapping("/bookmark")
public class FolderController {
	
	FolderService folderService =  new FolderService();
	
//	for get all Users
	
	@GetMapping("/showFolders")
	public List<Folder> showFoders() {
		return folderService.getAllFolders();
	}
	
//	@PostMapping("/createFolder")
//	public ResponseEntity<String> createFolder(@RequestBody Folder folder) {
//		if(folderService.createFolder(folder)) {
//			return ResponseEntity.ok().body("Folder is created successfully");
//		} else {
//			return ResponseEntity.badRequest().body("Folder is not created successfully");
//		}
//	}
	
	// for create folder
	
	@PostMapping("/createFolder/{parentFolder}")
	public ResponseEntity<String> createFolder(@RequestBody Folder folder, @PathVariable String parentFolder ) {
		if(folderService.createFolder(folder, parentFolder)) {
			return ResponseEntity.ok().body("Folder is created successfully");
		} else {
			return ResponseEntity.badRequest().body("Folder is not created successfully");
		}
	}
	
//	for add bookmarks
	
	@PostMapping("/addBookmark/{folder}")
	public ResponseEntity<String> addBookmark(@PathVariable String folder, @RequestBody Bookmark bookmark) {
		if(folderService.selectFolder(folder, bookmark)) {
			return ResponseEntity.ok().body("Bookmark added successfully");
		} else {
			return ResponseEntity.badRequest().body("Bookmark failed");
		}
	}
	
//	for show folder
	
	@GetMapping("/showBookmarks/{folderName}")
	public List<Bookmark> showBookmarks(@PathVariable String folderName) {
		return folderService.searchPathandShowAllBookmarks(folderName);
	}
	
//	for delete folder
	
	@PostMapping("/deleteFolder/{folder}")
	public ResponseEntity<String> deleteFolder(@PathVariable String folder) {
		if(folderService.deleteFolder(folder)) {
			return ResponseEntity.ok().body("Deleted successfully");
		} else {
			return ResponseEntity.badRequest().body("Deletion failed");
		}
	}
}
