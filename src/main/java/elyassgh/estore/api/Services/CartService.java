package elyassgh.estore.api.Services;

import elyassgh.estore.api.Beans.Cart;

import java.util.List;
import java.util.Optional;

public interface CartService {

    // ***  Create & Update Services  ***
    public int save (Cart cart);

    // ***  Read Services  ***
    public Optional<Cart> findCartById (Long cartID);
    public Cart findCartByUser (Long id);
    public List<Cart> findCartsWithItemsMoreThan(Integer nbr_items);

    // ***  Delete Services  ***
    public int delete (Cart cart);

}
