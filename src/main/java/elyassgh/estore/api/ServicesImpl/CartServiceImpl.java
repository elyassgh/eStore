package elyassgh.estore.api.ServicesImpl;

import elyassgh.estore.api.Beans.Cart;
import elyassgh.estore.api.Beans.User;
import elyassgh.estore.api.Repositories.CartRepository;
import elyassgh.estore.api.Services.CartService;
import elyassgh.estore.api.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    public CartRepository repository;

    @Autowired
    public UserService userService;

    @Override
    public int save(User user) {
        try {
            Cart cart = new Cart(user);
            repository.save(cart);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Optional<Cart> findCartById(Long cartID) {
        return repository.findById(cartID);
    }

    @Override
    public Cart findCartByUser(Long id) {
        User user = userService.findById(id).orElseThrow(()-> new RuntimeException("User Not Found !")) ;
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
