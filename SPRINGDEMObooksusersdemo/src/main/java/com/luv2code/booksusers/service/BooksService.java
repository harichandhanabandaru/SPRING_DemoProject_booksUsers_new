package com.luv2code.booksusers.service;

import com.luv2code.booksusers.entity.Books;


import java.util.List;

public interface BooksService {

     List<Books> findAll();

     Books findById(int theId);

    void save(Books theEmployee);

     void deleteById(int theId);


      List<Books> searchBy(String theName);
}
