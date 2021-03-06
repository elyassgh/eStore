package elyassgh.estore.api.Beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_Id")
    private Long id;

    // Total price of All the items in the cart
    private Double amount;

    // Number of product Objects in the cart
    private Integer cardinal;

    // Using user id as cart id to optimize the storage space ( --> MapsId Magic )
    @OneToOne
    @MapsId
    private User user;

    @OneToMany(mappedBy = "cart")
    private List<Cart_Item> items;

    public Cart() {
    }

    public Cart(User user) {
        this.user = user;
        this.amount = 0.0;
        this.cardinal = 0;
        this.items = new ArrayList<Cart_Item>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getCardinal() {
        return cardinal;
    }

    public void setCardinal(Integer cardinal) {
        this.cardinal = cardinal;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Cart_Item> getItems() {
        return items;
    }

    public void setItems(List<Cart_Item> items) {
        this.items = items;
    }

}
