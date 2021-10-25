package com.luv2code.booksusers.converter;

import com.luv2code.booksusers.dto.BooksDto;
import com.luv2code.booksusers.entity.Books;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BooksConverter {
    public BooksDto entityToDto(Books books)
    {
        BooksDto dto=new BooksDto();
        dto.setId(books.getId());
        dto.setPrice(books.getPrice());
        dto.setTitle(books.getTitle());

        return dto;
    }
    public List<BooksDto> entityToDto(List<Books> books)
    {
        return books.stream().map(x->entityToDto(x)).collect(Collectors.toList());
    }


    public Books dtoToEntity(BooksDto dto)
    {
        Books books=new Books();
        books.setId(dto.getId());
        books.setPrice(dto.getPrice());
        books.setTitle(dto.getTitle());
        return books;
    }
    public List<Books> dtoToEntity(List<BooksDto> dto)
    {
        return dto.stream().map(x->dtoToEntity(x)).collect(Collectors.toList());
    }
}
