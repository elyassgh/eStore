package elyassgh.estore.api.Services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import elyassgh.estore.api.Beans.ProductImage;

public interface ProductImageService {

    // ***  Upload Service  ***
    public int upload (MultipartFile uploadedImage, Long productObjectId) throws IOException;

    // ***  Retrieve Service  ***
    public ProductImage findById(Long id);
    public ProductImage getImage (Long productObjectId, String name) throws IOException;
    public List<ProductImage> getImages (Long productObjectId) throws IOException;

    // ***  Delete Service  ***
    public int delete (Long id);

    // STOPSHIP: 30/09/2020
    
}
