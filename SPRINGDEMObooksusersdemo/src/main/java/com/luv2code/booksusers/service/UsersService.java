package com.luv2code.booksusers.service;

import com.luv2code.booksusers.entity.Users;

import java.util.List;

public interface UsersService {

    public List<Users> findAll();

    public Users findById(int theId);

    public void save(Users theEmployee);

    public void deleteById(int theId);
    public Users findByEmail(String theId);


}
