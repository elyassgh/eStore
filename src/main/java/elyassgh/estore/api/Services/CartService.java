package elyassgh.estore.api.Services;

import java.util.List;
import java.util.Optional;

import elyassgh.estore.api.Beans.Cart;
import elyassgh.estore.api.Beans.User;

public interface CartService {

    // ***  Create & Update Services  ***
    public int save (User user);
    public int update(Cart cart);

    // ***  Read Services  ***
    public Optional<Cart> findCartById (Long cartID);
    public Cart findCartByUser (Long id);
    public List<Cart> findCartsWithItemsMoreThan(Integer nbr_items);

    // ***  Delete Services  ***
    public int delete (Cart cart);

}
