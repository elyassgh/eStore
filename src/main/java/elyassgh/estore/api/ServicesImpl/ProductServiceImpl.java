package elyassgh.estore.api.ServicesImpl;

import elyassgh.estore.api.Beans.Product;
import elyassgh.estore.api.Beans.ProductImage;
import elyassgh.estore.api.Exception.classes.NotFoundException;
import elyassgh.estore.api.Repositories.ProductRepository;
import elyassgh.estore.api.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    public ProductRepository repository;

    //Create a product without an image
    @Override
    public int save(String sku, String name, String brand, String type,
                    String category, String description, String phrase) {
        try {
            repository.save(new Product(sku,name,brand,type,category,description,phrase,null));
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int updateThumbImage(String sku, ProductImage productImage) {
        Product product = Optional.ofNullable(findBySKU(sku)).orElseThrow(() -> new NotFoundException("Product #"+ sku +" Not Found"));
        product.setProductImage(productImage);
        try {
            repository.save(product);
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public int updateDescAndPhrase(String sku, String description, String phrase) {
        Product product = Optional.ofNullable(findBySKU(sku)).orElseThrow(() -> new NotFoundException("Product #"+ sku +" Not Found"));
        if (description==null) {
            product.setPhrase(phrase);
            repository.save(product);
            return 1;
        } else if (phrase==null) {
            product.setDescription(description);
            repository.save(product);
            return 2;
        } else {
            product.setDescription(description);
            product.setPhrase(phrase);
            repository.save(product);
            return 3;
        }
    }

    @Override
    public Product findBySKU(String sku) {
        return repository.findBySku(sku);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
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
