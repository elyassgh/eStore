package elyassgh.estore.api.ServicesImpl;

import elyassgh.estore.api.Beans.Product;
import elyassgh.estore.api.Beans.ProductObject;
import elyassgh.estore.api.Repositories.ProductObjectRepository;
import elyassgh.estore.api.Services.ProductObjectService;
import elyassgh.estore.api.Services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductObjectServiceImpl implements ProductObjectService {

    @Autowired
    public ProductObjectRepository repository;

    @Autowired
    public ProductService productService;

    @Override
    public int save(String sku, String size, String colour, Integer quantity, Double price) {

        Product product = Optional.ofNullable(productService.findBySKU(sku))
                .orElseThrow(() -> new RuntimeException("Product Not Found"));

        ProductObject productObject = new ProductObject(size, colour, quantity, price, product);

        try {
            repository.save(productObject);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Optional<ProductObject> findPOById(Long productObjectId) {
        return repository.findById(productObjectId);
    }

    @Override
    public List<ProductObject> findPOsOfProduct(String sku) {

        Product product = Optional.ofNullable(productService.findBySKU(sku))
                .orElseThrow(() -> new RuntimeException("Product Not Found"));

        return repository.findProductObjectsByProduct(product);
    }

    @Override
    public List<ProductObject> findPOsOfProductAndQtyGE(String sku, Integer quantity) {

        Product product = Optional.ofNullable(productService.findBySKU(sku))
                .orElseThrow(() -> new RuntimeException("Product Not Found"));

        return repository.findByProductAndQuantityGreaterThanEqual(product, quantity);
    }

    @Override
    public List<ProductObject> findSoldPOs() {
        return repository.findSoldPOs();
    }

    @Override
    public List<ProductObject> findSoonSoldPOs() {
        return repository.findSoonSoldPOs();
    }

    @Override
    public List<ProductObject> findAvailablePOs(Double start, Double end) {
        return repository.findAvailablePOs(start, end);
    }

    @Override
    public int delete(Long productObjectId) {

        ProductObject productObject = repository.findById(productObjectId)
                .orElseThrow(() -> new RuntimeException("ProductObject Not Found !"));

        try {
            repository.save(productObject);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
