package com.luv2code.booksusers.api;

import com.luv2code.booksusers.converter.BooksConverter;
import com.luv2code.booksusers.dao.BooksRepository;
import com.luv2code.booksusers.dto.BooksDto;
import com.luv2code.booksusers.entity.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BooksController1 {

    @Autowired
    BooksRepository booksRepository;

    @Autowired
    BooksConverter converter;

    @GetMapping("/findAll1")
    public List<BooksDto> findAll()
    {
        List<Books> findAll =booksRepository.findAll();
        return converter.entityToDto(findAll);
    }

    @GetMapping("/find1/{ID}")
    public BooksDto findById(@PathVariable(value="ID") int id)
    {
        Books orElse=booksRepository.findById(id).orElse(null);
        return converter.entityToDto(orElse);
    }

    @PutMapping("/update1/{ID}")
    public BooksDto update(@PathVariable(value="ID") int id){
        Books books=booksRepository.findById(id).orElse(null);
        assert books != null;
        books=booksRepository.save(books);
        return converter.entityToDto(books);
    }

    @PostMapping("/save1")
        public BooksDto savebooks(@RequestBody BooksDto dto)
    {
        Books books = converter.dtoToEntity(dto);
        books=booksRepository.save(books);
        return converter.entityToDto(books);
    }




}
