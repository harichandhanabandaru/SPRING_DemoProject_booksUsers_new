package com.luv2code.booksusers.service;

import com.luv2code.booksusers.dao.BooksRepository;
import com.luv2code.booksusers.entity.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BooksServiceImpl  implements  BooksService{

    private BooksRepository booksRepository;


    @Autowired
    public BooksServiceImpl(BooksRepository theBooksRepository) {
        booksRepository = theBooksRepository;
    }

    @Override
    public List<Books> findAll() {
        return booksRepository.findAll();
    }

    @Override
    public Books findById(int theId) {

        Optional<Books> result = booksRepository.findById(theId);

        Books theBooks = null;
       if (result.isPresent())
            {
                theBooks = result.get();
             }
       else {
         throw new  IllegalStateException("Did not find books id - " + theId + "\n\n" + "\n\n");
        }


        return theBooks;
    }



    @Override
    public void save(Books theBooks) {
        booksRepository.save(theBooks);
    }

    @Override
    public void deleteById(int theId) {
        booksRepository.deleteById(theId);
    }

    @Override
    public List<Books> searchBy(String theName) {

        List<Books> results = null;

        if (theName != null && (theName.trim().length() > 0)) {
            results = booksRepository.findByIdContainsOrTitleContainsAllIgnoreCase(theName, theName);
        }
        else {
            results = findAll();
        }
        return results;
    }


}

