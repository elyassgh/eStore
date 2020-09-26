package elyassgh.estore.api.Beans;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Added timestamp
    private LocalDateTime added_at;

    // Still Available ?!
    private Boolean is_available;

    // If it was not available, Then when ?! --> it could be N/A ( --> null )
    @Column(nullable = true)
    private LocalDate available_date;

    @ManyToOne
    @JoinColumn(name = "user_Id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "productObject_Id")
    private ProductObject productObject;

    public Favorite() {
    }

    public Favorite(Long id, LocalDateTime added_at, Boolean is_available,
                    LocalDate available_date, User user, ProductObject productObject) {
        this.id = id;
        this.added_at = added_at;
        this.is_available = is_available;
        this.available_date = available_date;
        this.user = user;
        this.productObject = productObject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getAdded_at() {
        return added_at;
    }

    public void setAdded_at(LocalDateTime added_at) {
        this.added_at = added_at;
    }

    public Boolean getIs_available() {
        return is_available;
    }

    public void setIs_available(Boolean is_available) {
        this.is_available = is_available;
    }

    public LocalDate getAvailable_date() {
        return available_date;
    }

    public void setAvailable_date(LocalDate available_date) {
        this.available_date = available_date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ProductObject getProductObject() {
        return productObject;
    }

    public void setProductObject(ProductObject productObject) {
        this.productObject = productObject;
    }
}
