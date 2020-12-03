package elyassgh.estore.api.Beans;

import javax.persistence.*;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Unique String identifier Representing the product (Barcode)
    @Column(unique = true)
    private String sku;

    // Name of the Product
    private String name;

    // Brand Name of the Product (if applicable !)
    @Column(nullable = true)
    private String brand;

    // Jacket, Coat, Suit, shoes, dress, ...
    private String type;

    // Product Category : Male, Female, Kids, Accessories, Perfumes, ...
    private String category;

    // Small description about the product
    private String description;

    // Product appealing front phrase
    private String phrase;

    // Uni-directional OneToOne Relationship.
    // Product Thumbnail Image ( Fk --> Pointing to an existing Product Object Image)
    @OneToOne( fetch=FetchType.EAGER )
    // Eager Fetch type will make the Product Image Object retrieved at once with the Product Object
    // IMPORTANT !!! --> This Attribute represent a pointer to one the product objects of this product model
    @JoinColumn(name="Image_id")
    private ProductImage productImage;

    // Product Objects Mapping list (Bi-directional Relationship)
    @OneToMany(mappedBy = "product" , orphanRemoval = true)
    private List<ProductObject> productObjects;

    public Product() {
    }

    public Product(String sku, String name, String brand, String type,
                   String category, String description, String phrase,
                   ProductImage productImage) {
        this.sku = sku;
        this.name = name;
        this.brand = brand;
        this.type = type;
        this.category = category;
        this.description = description;
        this.phrase = phrase;
        this.productImage = productImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String product_sku) {
        this.sku = product_sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String product_name) {
        this.name = product_name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String product_brand) {
        this.brand = product_brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String product_type) {
        this.type = product_type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category_name) {
        this.category = category_name;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public ProductImage getProductImage() {
        return productImage;
    }

    public void setProductImage(ProductImage productImage) {
        this.productImage = productImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String product_description) {
        this.description = product_description;
    }

    public List<ProductObject> getProductObjects() {
        return productObjects;
    }

    public void setProductObjects(List<ProductObject> productObjects) {
        this.productObjects = productObjects;
    }
}

