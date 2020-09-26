package elyassgh.estore.api.Beans;

import javax.persistence.*;

@Entity
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Product image name
    private String image_name;

    // Product image type (Mime Type)
    private String image_type;

    // Product image binary content
    @Column(length = 1000)
    private byte[] image_byte_array;

    //Fk to ProductObject
    @ManyToOne
    @JoinColumn(name = "productObject_Id")
    private ProductObject productObject;

    public ProductImage() {
    }

    public ProductImage(Long id, String image_name, String image_type, byte[] image_byte_array, ProductObject productObject) {
        this.id = id;
        this.image_name = image_name;
        this.image_type = image_type;
        this.image_byte_array = image_byte_array;
        this.productObject = productObject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public String getImage_type() {
        return image_type;
    }

    public void setImage_type(String image_type) {
        this.image_type = image_type;
    }

    public byte[] getImage_byte_array() {
        return image_byte_array;
    }

    public void setImage_byte_array(byte[] image_byte_array) {
        this.image_byte_array = image_byte_array;
    }

    public ProductObject getProductObject() {
        return productObject;
    }

    public void setProductObject(ProductObject productObject) {
        this.productObject = productObject;
    }
}
