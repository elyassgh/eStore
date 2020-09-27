package elyassgh.estore.api.Beans;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Added date
    private Date addedAt;

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

    public Favorite(Long id, Date addedAt, Boolean isAvailable,
                    LocalDate availableDate, User user, ProductObject productObject) {
        this.id = id;
        this.addedAt = addedAt;
        this.isAvailable = isAvailable;
        this.availableDate = availableDate;
        this.user = user;
        this.productObject = productObject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Date added_at) {
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
