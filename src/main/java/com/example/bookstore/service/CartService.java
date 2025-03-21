package com.example.bookstore.service;

import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.CartItem;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookRepository bookRepository;

    public void addToCart(Long bookId) {
        Optional<CartItem> existingCartItem = cartRepository.findByBookId(bookId);
        if (existingCartItem.isPresent()) {
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + 1);  // Add one more
        } else {
            Book book = bookRepository.findById(bookId).orElse(null);
            if (book != null) {
                CartItem newItem = new CartItem(book, 1);  // First time add with quantity 1
                cartRepository.save(newItem);
            }
        }
    }

    public List<CartItem> getCartItems() {
        return cartRepository.findAll();
    }

    public double getTotalBill() {
        return cartRepository.findAll().stream()
                .mapToDouble(item -> item.getBook().getPrice() * item.getQuantity())
                .sum();
    }

    public int getCartItemCount() {
        return cartRepository.findAll().stream()
                .mapToInt(CartItem::getQuantity)
                .sum();
    }
}
