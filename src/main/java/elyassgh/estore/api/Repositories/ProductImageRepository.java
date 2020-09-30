package elyassgh.estore.api.Repositories;

import elyassgh.estore.api.Beans.ProductImage;
import elyassgh.estore.api.Beans.ProductObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    // Find an image by its name ( make it easy to find a specific image --> Depends on naming strategy ! )
    ProductImage findByProductObjectAndImageName (ProductObject productObject, String name);

    // Find all images of a specific product object
    List<ProductImage> findProductImagesByProductObject (ProductObject productobject);

    // STOPSHIP: 30/09/2020


}
