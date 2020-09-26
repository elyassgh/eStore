package elyassgh.estore.api.Repositories;

import elyassgh.estore.api.Beans.Cart;
import elyassgh.estore.api.Beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    // Find the cart of a specific user
    Cart findByUser(User user);

    // Find non empty carts
    List<Cart> findByCardinalGreaterThanOrderByAmountDesc(Integer nbr);


}
