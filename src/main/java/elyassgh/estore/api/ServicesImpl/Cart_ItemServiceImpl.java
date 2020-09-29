package elyassgh.estore.api.ServicesImpl;

import elyassgh.estore.api.Beans.Cart;
import elyassgh.estore.api.Beans.Cart_Item;
import elyassgh.estore.api.Repositories.Cart_ItemRepository;
import elyassgh.estore.api.Services.Cart_ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Cart_ItemServiceImpl implements Cart_ItemService {

    @Autowired
    public Cart_ItemRepository repository;

    @Override
    public int save(Cart_Item cart_item) {
        try {
            repository.save(cart_item);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<Cart_Item> findCart_ItemsByCart(Cart cart) {
        return repository.findCart_ItemsByCart(cart);
    }

    @Override
    public int update(Cart_Item cart_item) {
        Optional<Cart_Item> item = repository.findById(cart_item.getCartItem_Id());
        if (item.isPresent()) {
            try {
                repository.save(item.get());
                return 1;
            } catch (Exception e) {
                e.printStackTrace();
                return -1;
            }
        }
        return 0;
    }

    @Override
    public int delete(Cart_Item cart_item) {
        try {
            repository.delete(cart_item);
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public int deleteBatch(List<Cart_Item> items) {
        try {
            repository.deleteInBatch(items);
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

}
