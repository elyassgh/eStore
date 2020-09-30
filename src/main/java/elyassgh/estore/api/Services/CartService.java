package elyassgh.estore.api.Services;

import elyassgh.estore.api.Beans.Cart;
import elyassgh.estore.api.Beans.User;

import java.util.List;

public interface CartService {

    // ***  Create & Update Services  ***
    public int save (Cart cart);

    // ***  Read Services  ***
    public Cart findCartByUser (User user);
    public List<Cart> findCartsWithItemsMoreThan(Integer nbr_items);

    // ***  Delete Services  ***
    public int delete (Cart cart);

}
