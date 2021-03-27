package elyassgh.estore.api.Beans;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Added date
    private LocalDate addedAt;

    // Still Available ?!
    private Boolean isAvailable;

    // If it was not available, Then when ?! --> it could be N/A ( --> null )
    @Column(nullable = true)
    private LocalDate availableDate;

    @ManyToOne
    @JoinColumn(name = "user_Id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "productObject_Id")
    private ProductObject productObject;

    public Favorite() {
    }

    public Favorite(User user, ProductObject productObject) {
        this.addedAt = LocalDate.now();
        this.user = user;
        this.productObject = productObject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDate added_at) {
        this.addedAt = added_at;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean is_available) {
        this.isAvailable = is_available;
    }

    public LocalDate getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(LocalDate available_date) {
        this.availableDate = available_date;
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
