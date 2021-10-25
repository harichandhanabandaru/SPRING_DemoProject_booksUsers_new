package com.luv2code.booksusers.entity;


        import lombok.Getter;
        import lombok.Setter;

        import javax.persistence.*;
        import java.util.List;


        @Getter
        @Setter
@Entity
@Table(name="books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    int id;

    @Column(name="title")
    String title;

    @Column(name="price")
    int price;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="books_users",
            joinColumns = @JoinColumn(name="books_id"),
            inverseJoinColumns = @JoinColumn(name="users_id"))
    private List<Users> users;

    public Books()
    {}

    public Books(int id, String title, int price, List<Users> users) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.users = users;
    }

        }
