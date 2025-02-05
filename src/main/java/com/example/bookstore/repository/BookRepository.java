package com.example.bookstore.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.bookstore.entity.Book;


@Repository

public interface BookRepository extends JpaRepository<Book, Long> {

	@Query(value = "SELECT * FROM books "
			+ "WHERE CONCAT(books.name, ' ', books.author, ' ', books.price, ' ', books.quantity)"
			+ "LIKE %?1%", nativeQuery = true)
	public List<Book> findByKeyword(String keyword);
}

