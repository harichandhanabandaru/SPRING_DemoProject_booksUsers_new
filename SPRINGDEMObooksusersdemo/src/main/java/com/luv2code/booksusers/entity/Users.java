package com.luv2code.booksusers.entity;


        import lombok.Getter;
        import lombok.Setter;

        import javax.persistence.*;
        import java.util.ArrayList;
        import java.util.List;

        @Getter
        @Setter
@Entity
@Table(name="users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    int id;
    @Column(name="first_name")
    String firstName;

    @Column(name="last_name")
    String lastName;

    @Column(name="email",unique = true)
    String email;

    @Column(name="phonenumber")
    String phonenumber;

    @Column(name="password")
    String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="books_users",
            joinColumns = @JoinColumn(name="users_id"),
            inverseJoinColumns = @JoinColumn(name="books_id"))
    private List<Books> books;

    public void addBooks(Books theStudent)
    {
        if(books==null)
        {
            books=new ArrayList<>();
        }
        else{
            books.add(theStudent);
        }
    }

    public Users()
    {}

    public Users(int id, String firstName, String lastName, String email, String phonenumber, String password, List<Books> books) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phonenumber = phonenumber;
        this.password = password;
        this.books = books;
    }


}
