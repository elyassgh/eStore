package elyassgh.estore.api.Services;

import elyassgh.estore.api.Beans.Cart;
import elyassgh.estore.api.Beans.Cart_Item;

import java.util.List;

public interface Cart_ItemService {

    // ***  Create Services  ***
    public int save (Cart_Item cart_item);

    // ***  Read Services  ***
    List<Cart_Item> findCart_ItemsByCart (Cart cart);

    // ***  Update Services  ***
    public int update (Cart_Item cart_item);

    // ***  Delete Services  ***
    public int delete (Cart_Item cart_item);
    public int deleteBatch (List<Cart_Item> items);


}
