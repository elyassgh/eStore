package elyassgh.estore.api.Services;

import elyassgh.estore.api.Beans.Product;
import elyassgh.estore.api.Beans.ProductImage;

import java.util.List;

public interface ProductService {

    // ***  Create & Update Services  ***
    public int save (String sku, String name, String brand, String type,
                     String category, String description, String phrase);
    public int updateThumbImage (String sku, ProductImage productImage);

    // ***  Read Services  ***
    public Product findBySKU (String sku);
    public List<Product> findProductsByBrand (String brand);
    public List<Product> findProductsByCategory (String category);
    public List<Product> findProductByCatAndBrand (String category, String brand);
    public List<Product> findProductsByCatAndBrandAndType (String category, String brand, String type);

    // ***  Delete Services  ***
    public int delete (Product product);

}
