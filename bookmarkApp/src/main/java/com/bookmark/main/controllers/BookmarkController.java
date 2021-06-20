//package com.bookmark.main.controllers;
//
//import java.util.List;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.bookmark.main.models.Bookmark;
//import com.bookmark.main.services.BookmarkService;
//@RestController
////@RequestMapping("/bookmark")
//public class BookmarkController {
//	
//	BookmarkService bookmarkService =  new BookmarkService();
//	
//	@GetMapping("/showBookmarks/{folder}")
//	public List<Bookmark> showBookmarks(@PathVariable String folder) {
//		return bookmarkService.getAllBookmarks(folder);
//	}
//	
//	@PostMapping("/addBookmark/{folder}")
//	public ResponseEntity<String> addBookmark(@PathVariable String folder, @RequestBody Bookmark bookmark) {
//		bookmarkService.addBookmarks(folder, bookmark);
//		return ResponseEntity.ok().body("Bookmark added successfully");
//	}
//}
