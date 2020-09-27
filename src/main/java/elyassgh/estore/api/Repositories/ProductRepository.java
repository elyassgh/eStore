package elyassgh.estore.api.Repositories;

import elyassgh.estore.api.Beans.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Find product by SKU
    Product findBySku (String sku);

    // Find All products of a specific brand
    List<Product> findProductsByBrand (String brand);

    // Find All products of a specific category
    List<Product> findProductsByCategory (String category);

    // Find All products of a specific category and brand
    List<Product> findProductsByCategoryAndBrand (String category, String brand);

    // Find All products of a specific category, brand and type
    List<Product> findProductsByCategoryAndBrandAndType (String category, String brand, String type);

}
