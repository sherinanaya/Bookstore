package com.example.bookstore.controller;

import java.util.List;
import com.example.bookstore.entity.Book;
import com.example.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	//Display Home Page
	@GetMapping("/")
	public String displayHomePage(Model model) {
		List<Book> books = bookService.getAllBooks();
		
		model.addAttribute("book", books);
		return "home_page";
	}
	
	@GetMapping("/add_book")
	public String displayAddBookPage() {
		
		return "add_book_page";
	}
	
	//Save a new book
	@PostMapping("/save")
	public String saveBook(Model model, @ModelAttribute Book book) {  	
	    bookService.saveBook(book);
	    
		return "redirect:/";
	}
	
	//Delete a book
	@GetMapping("/del_book/{id}")
	public String delBookById(@PathVariable("id") Long id) {
		bookService.deleteBookById(id);
		return "redirect:/";
	}
		
	//Edit Book by ID
	@GetMapping("/edit_book/{id}")
	public String editBookById(Model model, @PathVariable("id") Long id) {
		Book book = bookService.getBookById(id);
		model.addAttribute("book", book);
		return "edit_book_page";
	}
}