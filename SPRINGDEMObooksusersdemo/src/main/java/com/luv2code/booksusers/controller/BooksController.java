package com.luv2code.booksusers.controller;

import com.luv2code.booksusers.converter.BooksConverter;
import com.luv2code.booksusers.entity.Books;
import com.luv2code.booksusers.service.BooksService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")

public class BooksController {

     String books="books";

    private BooksService booksService;


    public BooksController(BooksService theBooksService) {
        booksService = theBooksService;
    }



    @GetMapping("/list")
    public String listBooks(Model theModel) {

        // get employees from db
        List<Books> theBooks = booksService.findAll();

        // add to the spring model
        theModel.addAttribute(books, theBooks);

        return "books/list-books";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        Books theStaff = new Books();

        theModel.addAttribute(books, theStaff);

        return "/books/books-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("booksId") int theId,
                                    Model theModel) {

        Books theBooks = booksService.findById(theId);
        theModel.addAttribute(books, theBooks);
        return "/books/books-form";
    }


    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("books") Books theBooks) {

        // save the employee
        booksService.save(theBooks);

        // use a redirect to prevent duplicate submissions
        return "redirect:/books/list";
    }


    @GetMapping("/delete")
    public String delete(@RequestParam("booksId") int theId) {

        // delete the employee
        booksService.deleteById(theId);

        // redirect to /employees/list
        return "redirect:/books/list";

    }

@GetMapping("/search")
public String delete(@RequestParam("booksName") String theName,
                     Model theModel) {

    // delete the employee
    List<Books> theBooks = booksService.searchBy(theName);

    // add to the spring model
    theModel.addAttribute(books, theBooks);

    // send to /employees/list
    return "/books/searched-books";

}
}
