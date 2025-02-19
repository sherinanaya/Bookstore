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
	
	//@Autowired
	//private CartRepository cartRepository;

	//@Autowired
	//private CartItemRepository cartItemRepository;

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
	/*
	public Cart getCart(Long cartId) {
		return cartRepository.findById(cartId).orElseGet(() -> {
			Cart newCart = new Cart();
			return cartRepository.save(newCart);
		});
	}

	public Cart addToCart(Long cartId, Long bookId) {
		Cart cart = getCart(cartId);

		Optional<Book> bookOptional = bookRepository.findById(bookId);
		if (!bookOptional.isPresent()) {
			throw new RuntimeException("Book not found");
		}

		Book book = bookOptional.get();

		Optional<CartItem> existingCartItem = cart.getCartItems().stream()
				.filter(item -> item.getBook().getId().equals(bookId))
				.findFirst();

		if (existingCartItem.isPresent()) {
			existingCartItem.get().setQuantity(existingCartItem.get().getQuantity() + 1);
		} else {
			CartItem cartItem = new CartItem();
			cartItem.setBook(book);
			cartItem.setQuantity(1);
			cartItem.setCart(cart);
			cart.getCartItems().add(cartItem);
		}

		cart.setTotalPrice(cart.getCartItems().stream().mapToDouble(item -> item.getBook().getPrice() * item.getQuantity()).sum());
		return cartRepository.save(cart);
	}

	public List<CartItem> getCartItems(Long cartId) {
		Cart cart = getCart(cartId);
		return cart.getCartItems().stream().filter(item -> item.getQuantity() > 0).toList();
	}*/
}
