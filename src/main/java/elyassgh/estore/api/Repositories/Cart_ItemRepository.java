package elyassgh.estore.api.Repositories;

import elyassgh.estore.api.Beans.Cart;
import elyassgh.estore.api.Beans.Cart_Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Cart_ItemRepository extends JpaRepository<Cart_Item, Long> {

    // Find cart items of a specific cart
    List<Cart_Item> findCart_ItemsByCart(Cart cart);

}
