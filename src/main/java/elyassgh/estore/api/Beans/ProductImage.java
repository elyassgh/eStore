package elyassgh.estore.api.Beans;

import javax.persistence.*;

@Entity
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Image_id")
    private Long id;

    // Product image name
    private String imageName;

    // Product image type (Mime Type)
    private String imageType;

    // Product image binary content
    @Column(length = 1000)
    private byte[] imageBytesArray;

    //Fk to ProductObject
    @ManyToOne
    @JoinColumn(name = "productObject_Id")
    private ProductObject productObject;

    public ProductImage() {
    }

    public ProductImage(String imageName, String imageType, byte[] imageBytesArray, ProductObject productObject) {
        this.imageName = imageName;
        this.imageType = imageType;
        this.imageBytesArray = imageBytesArray;
        this.productObject = productObject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String image_name) {
        this.imageName = image_name;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String image_type) {
        this.imageType = image_type;
    }

    public byte[] getImageBytesArray() {
        return imageBytesArray;
    }

    public void setImageBytesArray(byte[] image_byte_array) {
        this.imageBytesArray = image_byte_array;
    }

    public ProductObject getProductObject() {
        return productObject;
    }

    public void setProductObject(ProductObject productObject) {
        this.productObject = productObject;
    }
}
