package elyassgh.estore.api.Beans;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ProductObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "productObject_Id")
    private Long id;

    // Sizes XS S M L XL XXL Standard ...
    private String size;

    // Product Colour
    private String colour;

    // Available Quantity of the current size and colour
    private Integer quantity;

    // Unit Price
    private Double price;

    //Fk to Product --> Bidirectional Relation
    @ManyToOne
    @JoinColumn(name = "product_Id")
    private Product product;

    @OneToMany
    private List<ProductImage> productImages;

    // Needed for mapping purposes --> Ignore it, and don't touch it !
    @OneToMany(mappedBy = "productObject")
    private List<Favorite> favorites;

    // Needed for mapping purposes --> Ignore it, and don't touch it !
    @OneToMany(mappedBy = "productObject")
    private List<Command_Item> items;

    public ProductObject() {
    }

    public ProductObject(Long id, String size, String colour, Integer quantity, Double price, Product product) {
        this.id = id;
        this.size = size;
        this.colour = colour;
        this.quantity = quantity;
        this.price = price;
        this.product = product;
        this.productImages = new ArrayList<ProductImage>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<ProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImage> product_images) {
        this.productImages = product_images;
    }

    public List<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Favorite> favorites) {
        this.favorites = favorites;
    }

    public List<Command_Item> getItems() {
        return items;
    }

    public void setItems(List<Command_Item> items) {
        this.items = items;
    }
}
