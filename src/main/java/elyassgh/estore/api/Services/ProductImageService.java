package elyassgh.estore.api.Services;

import elyassgh.estore.api.Beans.ProductImage;
import elyassgh.estore.api.Beans.ProductObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductImageService {

    // ***  Upload Service  ***
    public int upload (MultipartFile uploadedImage, ProductObject productObject) throws IOException;

    // ***  Retrieve Service  ***
    public ProductImage findById(Long id);
    public ProductImage getImage (ProductObject productObject, String name) throws IOException;
    public List<ProductImage> getImages (ProductObject productObject) throws IOException;

    // ***  Delete Service  ***
    public int delete (ProductImage productImage);

    // STOPSHIP: 30/09/2020
    
}
