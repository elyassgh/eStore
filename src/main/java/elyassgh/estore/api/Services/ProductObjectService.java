package elyassgh.estore.api.Services;

import elyassgh.estore.api.Beans.Product;
import elyassgh.estore.api.Beans.ProductObject;

import java.util.List;
import java.util.Optional;

public interface ProductObjectService {

    // ***  Create & Update Services  ***
    public int save (ProductObject productObject);

    // ***  Read Services  ***
    Optional<ProductObject> findPOById (Long productObjectId);
    List<ProductObject> findPOsOfProduct (Product product);
    List<ProductObject> findPOsOfProductAndQtyGE (Product product, Integer quantity);
    List<ProductObject> findSoldPOs ();
    List<ProductObject> findSoonSoldPOs ();
    List<ProductObject> findAvailablePOs (Double start, Double end);

    // ***  Delete Services  ***
    public int delete (ProductObject productObject);

}
