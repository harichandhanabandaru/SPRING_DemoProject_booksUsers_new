package com.luv2code.booksusers.dao;

import com.luv2code.booksusers.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BooksRepository extends JpaRepository<Books,Integer> {

    public List<Books> findByIdContainsOrTitleContainsAllIgnoreCase(String id, String title);

}
