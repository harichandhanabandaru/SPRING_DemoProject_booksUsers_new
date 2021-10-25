package com.luv2code.booksusers.service;

import com.luv2code.booksusers.dao.UsersRepository;
import com.luv2code.booksusers.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements  UsersService{
    private UsersRepository userRepository;

    @Autowired
    public UserServiceImpl(UsersRepository theUserRepository) {
        userRepository = theUserRepository;
    }

    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Users findById(int theId) {
        Optional<Users> result = userRepository.findById(theId);

        Users theUser = null;

        if (result.isPresent()) {
            theUser = result.get();
        }
        else {
            // we didn't find the employee
            throw new  IllegalStateException("Did not find books id - " + theId + "\n\n" + "\n\n");
        }

        return theUser;
    }

    @Override
    public Users findByEmail(String theId) {
        Optional<Users> result = Optional.ofNullable(userRepository.findByEmail(theId));

        Users theUser = null;

        if (result.isPresent()) {
            theUser = result.get();
        }
        else {
            // we didn't find the employee
            throw new  IllegalStateException("Did not find books id - " + theId + "\n\n" + "\n\n");
        }

        return theUser;
    }

    @Override
    public void save(Users theUser) {
        userRepository.save(theUser);
    }

    @Override
    public void deleteById(int theId) {
        userRepository.deleteById(theId);
    }




}
