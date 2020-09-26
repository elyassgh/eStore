package elyassgh.estore.api.Beans;

import javax.persistence.*;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Unique String identifier Representing the product
    private String product_sku;

    // Name of the Product
    private String product_name;

    // Brand Name of the Product (if applicable !)
    @Column(nullable = true)
    private String product_brand;

    // Jacket, Coat, Suit, shoes, dress, ...
    private String product_type;

    // Product Category : Male, Female, Kids, Accessories, Perfumes, ...
    private String category_name;

    // Small description about the product
    private String product_description;

    @OneToMany(mappedBy = "product")
    List<ProductObject> productObjects;

    public Product() {
    }

    public Product(Long id, String product_sku, String product_name, String product_brand,
                   String product_type, String category_name, String product_description,
                   List<ProductObject> productObjects) {
        this.id = id;
        this.product_sku = product_sku;
        this.product_name = product_name;
        this.product_brand = product_brand;
        this.product_type = product_type;
        this.category_name = category_name;
        this.product_description = product_description;
        this.productObjects = productObjects;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct_sku() {
        return product_sku;
    }

    public void setProduct_sku(String product_sku) {
        this.product_sku = product_sku;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_brand() {
        return product_brand;
    }

    public void setProduct_brand(String product_brand) {
        this.product_brand = product_brand;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public List<ProductObject> getProductObjects() {
        return productObjects;
    }

    public void setProductObjects(List<ProductObject> productObjects) {
        this.productObjects = productObjects;
    }
}

