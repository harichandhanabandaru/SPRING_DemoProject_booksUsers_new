package com.luv2code.booksusers.dao;

import com.luv2code.booksusers.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface UsersRepository extends JpaRepository<Users,Integer> {

    @Query("SELECT u FROM Users u WHERE u.email = ?1")
    public Users findByEmail(String email);

}
