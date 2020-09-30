package elyassgh.estore.api.ServicesImpl;

import elyassgh.estore.api.Beans.Product;
import elyassgh.estore.api.Repositories.ProductRepository;
import elyassgh.estore.api.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    public ProductRepository repository;

    @Override
    public int save(Product product) {
        try {
            repository.save(product);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Product findBySKU(String sku) {
        return repository.findBySku(sku);
    }

    @Override
    public List<Product> findProductsByBrand(String brand) {
        return repository.findProductsByBrand(brand);
    }

    @Override
    public List<Product> findProductsByCategory(String category) {
        return repository.findProductsByCategory(category);
    }

    @Override
    public List<Product> findProductByCatAndBrand(String category, String brand) {
        return repository.findProductsByCategoryAndBrand(category, brand);
    }

    @Override
    public List<Product> findProductsByCatAndBrandAndType(String category, String brand, String type) {
        return repository.findProductsByCategoryAndBrandAndType(category, brand, type);
    }

    @Override
    public int delete(Product product) {
        try {
            repository.delete(product);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
