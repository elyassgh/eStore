package elyassgh.estore.api.Services;

import java.util.List;
import java.util.Optional;

import elyassgh.estore.api.Beans.ProductObject;

public interface ProductObjectService {

    // ***  Create & Update Services  ***
    public int save (String sku, String size, String colour, Integer quantity, Double price);

    // ***  Read Services  ***
    Optional<ProductObject> findPOById (Long productObjectId);
    List<ProductObject> findPOsOfProduct (String sku);
    List<ProductObject> findPOsOfProductAndQtyGE (String sku, Integer quantity);
    List<ProductObject> findSoldPOs ();
    List<ProductObject> findSoonSoldPOs ();
    List<ProductObject> findAvailablePOs (Double start, Double end);

    // ***  Delete Services  ***
    public int delete (Long productObjectId);
}
