package elyassgh.estore.api.Repositories;

import elyassgh.estore.api.Beans.Product;
import elyassgh.estore.api.Beans.ProductObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductObjectRepository extends JpaRepository<ProductObject,Long> {

    // Find product objects of a specific product
    List<ProductObject> findProductObjectsByProduct(Product product);

    // Find product objects of a specific product having a least a specific quantity
    List<ProductObject> findByProductAndQuantityGreaterThanEqual(Product product, Integer quantity);

    // Find all sold (qty=0) product objects
    @Query("SELECT po FROM ProductObject po WHERE po.quantity = 0 ")
    List<ProductObject> findSoldPOs ();

    // Find all product object with a quantity less than 10 units
    @Query("SELECT po FROM ProductObject po WHERE po.quantity <= 10 ")
    List<ProductObject> findSoonSoldPOs ();

    // Find all available (qty>0) product objects where price is between two values
    @Query("SELECT po FROM ProductObject po WHERE po.quantity > 0 AND po.price BETWEEN ?1 AND ?2")
    List<ProductObject> findAvailablePOs (Double start, Double end);

}
