package com.luv2code.booksusers.controller;

import com.luv2code.booksusers.config.UsersUserDetails;
import com.luv2code.booksusers.entity.Books;
import com.luv2code.booksusers.entity.Users;
import com.luv2code.booksusers.service.BooksService;
import com.luv2code.booksusers.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserPageController {

     String books="books";
    @Autowired
    private UsersService service;

    @Autowired
    private BooksService booksService;

    public UserPageController(BooksService thebooksService) {
        booksService = thebooksService;
    }


    @GetMapping("/userpage")
    public String userpage(Model theModel)
    {

        return "userpage" ;
    }


    @GetMapping("/user-detail-page")
    public String sayHellouserdetails(Model theModel)
    {

        return "user-details-page";
    }

    @GetMapping("/user-account-details")
    public String viewDetails(@AuthenticationPrincipal UsersUserDetails loggedUser, Model theModel)
    {
        String email=loggedUser.getUsername();
        Users user=service.findByEmail(email);
        theModel.addAttribute("user",user);
        return "update-user-details" ;
    }

    @PostMapping("/user-account-details/update")
    public String saveDetails(Users user, RedirectAttributes redirectAttributes,
                              @AuthenticationPrincipal UsersUserDetails loggedUser
    )
    {
        loggedUser.setFirstName(user.getFirstName());
        loggedUser.setLastName(user.getLastName());
        loggedUser.setPhoneNumber(user.getPhonenumber());
        Users u = service.findByEmail(user.getEmail());
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
        u.setPhonenumber(user.getPhonenumber());
        service.save(u);
        redirectAttributes.addFlashAttribute("message","Your details have been updated");

        return "user-details-page";
    }

    @GetMapping("/bookslist")
    public String listBooks(Model theModel) {

        List<Books> theBooks = booksService.findAll();
        // add to the spring model
        theModel.addAttribute(books, theBooks);
        return books;

    }
    @GetMapping("/view-user-books")
    public String viewBooksDetails(@AuthenticationPrincipal UsersUserDetails loggedUser, Model theModel)
    {
        String email=loggedUser.getUsername();
        Users user=service.findByEmail(email);
        List<Books> booksUser=user.getBooks();

        theModel.addAttribute(books,booksUser);
        return "view-user-books" ;
    }

    @GetMapping("/buybooks")
    public String buyBooks(@ModelAttribute("books") Books theBooks) {
        return "redirect:/books/list";

    }
    @GetMapping("/buybookshello")
    public String showFormForUpdate(@RequestParam("booksId") int theId,@AuthenticationPrincipal UsersUserDetails loggedUser,
                                    Model theModel) {


        String email=loggedUser.getUsername();
        Users user=service.findByEmail(email);
        Books booksFinded= booksService.findById(theId);
        List<Books> bookslist=user.getBooks();
        int flag=0;
        for(int i=0;i<bookslist.size();i++)
        {
            if(bookslist.get(i)==booksFinded)
            {
                flag=1;
            }
        }
        if(flag==0){

            user.addBooks(booksFinded);
            service.save(user);
            booksService.save(booksFinded);
        }

        List<Books> theBooks = booksService.findAll();

        // add to the spring model
        theModel.addAttribute(books, theBooks);

        return books;
    }
    @GetMapping("/nonuserbooks")
    public String viewNonUserBooksDetails(@AuthenticationPrincipal UsersUserDetails loggedUser, Model theModel)
    {
        String email=loggedUser.getUsername();
        Users user=service.findByEmail(email);
        List<Books> userBooks=user.getBooks();

        List<Books> theBooks = booksService.findAll();
        List<Books> newBooks=new ArrayList<>();

        for(int i=0;i<theBooks.size();i++)
        {
            newBooks.add(theBooks.get(i));
        }


        for(int i=0;i<userBooks.size();i++)
        {
            if(newBooks.contains(userBooks.get(i)))
            {
                newBooks.remove(userBooks.get(i));
            }
        }
        // add to the spring model
        theModel.addAttribute(books, newBooks);

        return "non-user-books" ;
    }


    @GetMapping("/buybookselect")
    public String viewNonUserBooksDetails(@AuthenticationPrincipal UsersUserDetails loggedUser, Model theModel,@RequestParam("booksId") int theId)
    {
        String email=loggedUser.getUsername();
        Users user=service.findByEmail(email);
        Books books1= booksService.findById(theId);

        user.addBooks(books1);
        service.save(user);
        booksService.save(books1);

        // add to the spring model
        theModel.addAttribute(books, books1);

        return "brought-book";
    }



    @GetMapping("/searchbooks")
    public String delete(@RequestParam("booksName") String theName,@AuthenticationPrincipal UsersUserDetails loggedUser,
                         Model theModel) {

        List<Books> theBookSearch = booksService.searchBy(theName);

        String email=loggedUser.getUsername();
        Users user=service.findByEmail(email);
        List<Books> userBooks=user.getBooks();

        List<Books> theBooks = booksService.findAll();
        List<Books> newBooks=new ArrayList<>();
        List<Books> nobooks=new ArrayList<>();

        for(int i=0;i<theBooks.size();i++)
        {
            newBooks.add(theBooks.get(i));
        }


        for(int i=0;i<userBooks.size();i++)
        {
            if(newBooks.contains(userBooks.get(i)))
            {
                newBooks.remove(userBooks.get(i));
            }
        }

        int flag=0;
        if(newBooks.contains(theBookSearch .get(0)))
        {
            flag=1;

        }
        if(flag==1)
        { theModel.addAttribute(books, theBookSearch );}
        else {
            theModel.addAttribute(books, nobooks);
        }


        return "searched-books";



    }



}
