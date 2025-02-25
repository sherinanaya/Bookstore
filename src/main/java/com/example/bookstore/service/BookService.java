package com.example.bookstore.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bookstore.entity.Book;
import com.example.bookstore.repository.BookRepository;
//import com.example.bookstore.repository.CartRepository;
//import com.example.bookstore.repository.CartItemRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	public List<Book> getAllBooks() {
		List<Book> books = bookRepository.findAll();
		return books;
	}
	
	public Book getBookById(Long Id) {
		Book book = bookRepository.findById(Id).orElse(null);
    	return book;
	}
	
	public void saveBook(Book book) {
		bookRepository.save(book);
	}
	
	public void deleteBookById(Long id) {
		bookRepository.deleteById(id);	
	}
	
	public void editBookById(Long Id) {
		bookRepository.save(getBookById(Id));
	}
	
	public List<Book> searchAllBooks(String keyword) {
		if (!keyword.isEmpty())
			return (List<Book>) bookRepository.findByKeyword(keyword);
		else
			return (List<Book>) bookRepository.findAll();
	}
	
}
