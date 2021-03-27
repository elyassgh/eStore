package elyassgh.estore.api.Services;

import java.util.List;

import elyassgh.estore.api.Beans.Cart;
import elyassgh.estore.api.Beans.Cart_Item;

public interface Cart_ItemService {

    // ***  Create & Update Services  ***
    public int save (Long productObjectId, Long cartId, Integer quantity);
    public int update (Long productObjectId, Long cartId, Integer quantity);

    // ***  Read Services  ***
    public List<Cart_Item> findCart_ItemsByCart (Cart cart);
    public Cart_Item findByCartAndPO (Long cartId, Long productObjectId);

    // ***  Delete Services  ***
    public int delete (Cart_Item cart_item);
    public int deleteBatch (List<Cart_Item> items);


}
