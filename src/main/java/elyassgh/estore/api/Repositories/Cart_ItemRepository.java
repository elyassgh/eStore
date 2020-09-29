package elyassgh.estore.api.Repositories;

import elyassgh.estore.api.Beans.Cart;
import elyassgh.estore.api.Beans.Cart_Item;
import elyassgh.estore.api.Beans.Cart_Items_Key;
import elyassgh.estore.api.Beans.ProductObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Cart_ItemRepository extends JpaRepository<Cart_Item, Cart_Items_Key> {

    // Find cart items of a specific cart
    List<Cart_Item> findCart_ItemsByCart(Cart cart);

    // Find By Id (Composed Primary Key !)
    Cart_Item findCart_ItemByCartAndProductObject (Cart cart, ProductObject productobject);

}
