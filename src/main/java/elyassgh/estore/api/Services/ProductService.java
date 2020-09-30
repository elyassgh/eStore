package elyassgh.estore.api.Services;

import elyassgh.estore.api.Beans.Product;

import java.util.List;

public interface ProductService {

    // ***  Create & Update Services  ***
    public int save (Product product);

    // ***  Read Services  ***
    public Product findBySKU (String sku);
    public List<Product> findProductsByBrand (String brand);
    public List<Product> findProductsByCategory (String category);
    public List<Product> findProductByCatAndBrand (String category, String brand);
    public List<Product> findProductsByCatAndBrandAndType (String category, String brand, String type);

    // ***  Delete Services  ***
    public int delete (Product product);

}
