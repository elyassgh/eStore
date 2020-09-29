package elyassgh.estore.api.Beans;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //User name and login
    @Column(unique = true)
    private String username;

    //User password
    private String password;

    //User email (For email marketing and notifications)
    @Column(nullable = true , unique = true)
    private String email;

    private Date addedAt;

    @OneToOne(mappedBy = "user")
    private Cart cart;

    @OneToMany(mappedBy = "user")
    private List<Favorite> favorites;


    @OneToMany(mappedBy = "user")
    private List<Command> commands;  // --> Orders List ;)

    public User() {
    }

    public User(Long id, String username, String password, String email,
                Date addedAt, Cart cart, List<Favorite> favorites,
                List<Command> commands) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.addedAt = addedAt;
        this.cart = cart;
        this.favorites = favorites;
        this.commands = commands;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Date addedAt) {
        this.addedAt = addedAt;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Favorite> favorites) {
        this.favorites = favorites;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }
}
