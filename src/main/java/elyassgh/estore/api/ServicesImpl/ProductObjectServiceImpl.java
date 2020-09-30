package elyassgh.estore.api.ServicesImpl;

import elyassgh.estore.api.Beans.Product;
import elyassgh.estore.api.Beans.ProductObject;
import elyassgh.estore.api.Repositories.ProductObjectRepository;
import elyassgh.estore.api.Services.ProductObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductObjectServiceImpl implements ProductObjectService {

    @Autowired
    public ProductObjectRepository repository;

    @Override
    public int save(ProductObject productObject) {
        try {
            repository.save(productObject);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<ProductObject> findPOsOfProduct(Product product) {
        return repository.findProductObjectsByProduct(product);
    }

    @Override
    public List<ProductObject> findPOsOfProductAndQtyGE(Product product, Integer quantity) {
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
    public int delete(ProductObject productObject) {
        try {
            repository.save(productObject);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
