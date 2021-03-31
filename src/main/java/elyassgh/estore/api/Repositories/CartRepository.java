package elyassgh.estore.api.Repositories;

import elyassgh.estore.api.Beans.Cart;
import elyassgh.estore.api.Beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    // Find the cart of a specific user
    Cart findCartByUser(User user);

    // Find carts where number of items is more than a specific number (nbr)
    // --> for non empty carts  ( cardinal > 0 ) <=> nbr = 0
    List<Cart> findByCardinalGreaterThanOrderByAmountDesc(Integer nbr);

}
