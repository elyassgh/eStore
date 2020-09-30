package elyassgh.estore.api.ServicesImpl;

import elyassgh.estore.api.Beans.Cart;
import elyassgh.estore.api.Beans.User;
import elyassgh.estore.api.Repositories.CartRepository;
import elyassgh.estore.api.Services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    public CartRepository repository;

    @Override
    public int save(Cart cart) {
        try {
            repository.save(cart);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Cart findCartByUser(User user) {
        return repository.findCartByUser(user);
    }

    @Override
    public List<Cart> findCartsWithItemsMoreThan(Integer nbr_items) {
        return repository.findByCardinalGreaterThanOrderByAmountDesc(nbr_items);
    }


    @Override
    public int delete(Cart cart) {
        try {
            repository.delete(cart);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
