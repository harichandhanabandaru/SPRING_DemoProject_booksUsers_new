package com.luv2code.booksusers.config;
import com.luv2code.booksusers.entity.Users;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UsersUserDetails implements UserDetails {
    private Users users;

    public UsersUserDetails(Users user) {
        this.users = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



    public String getFullName() {
        return users.getFirstName() + " " + users.getLastName();
    }

    public void setFirstName(String firstName)
    {
        this.users.setFirstName(firstName);
    }
    public void setLastName(String lastName)
    {
        this.users.setLastName(lastName);
    }
    public void setPhoneNumber(String phonenumber)
    {
        this.users.setPhonenumber(phonenumber);
    }
    public void setEmail(String email)
    {
        this.users.setEmail(email);
    }
    public void setPassword(String password)
    {
        this.users.setPassword(password);
    }


    public Users getUser()
    {
        return this.users;
    }

}
