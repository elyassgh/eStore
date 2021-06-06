package elyassgh.estore.api.ServicesImpl;

import elyassgh.estore.api.Beans.Cart;
import elyassgh.estore.api.Beans.Cart_Item;
import elyassgh.estore.api.Beans.ProductObject;
import elyassgh.estore.api.Exception.classes.NotFoundException;
import elyassgh.estore.api.Repositories.Cart_ItemRepository;
import elyassgh.estore.api.Services.CartService;
import elyassgh.estore.api.Services.Cart_ItemService;
import elyassgh.estore.api.Services.ProductObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Cart_ItemServiceImpl implements Cart_ItemService {

    @Autowired
    public Cart_ItemRepository repository;

    @Autowired
    public ProductObjectService productObjectService;

    @Autowired
    public CartService cartService;

    @Override
    public int save(Long productObjectId, Long cartId, Integer quantity) {
        ProductObject productObject = productObjectService.findPOById(productObjectId).orElseThrow(()-> new NotFoundException("ProductObject #"+ productObjectId +" Not Found") );
        Cart cart = cartService.findCartById(cartId).orElseThrow(()-> new NotFoundException("Cart #"+ cartId +" Not Found"));
            try {
                repository.save(new Cart_Item(productObject, cart, quantity));
                cartService.update(cart);
                return 1;
            } catch (Exception e) {
                e.printStackTrace();
                return -1;
            }
    }


    @Override
    public int update(Long productObjectId, Long cartId, Integer quantity) {
        Cart cart = cartService.findCartById(cartId).orElseThrow(()-> new NotFoundException("Cart #"+ cartId +" Not Found"));
        ProductObject productObject = productObjectService.findPOById(productObjectId).orElseThrow(()-> new NotFoundException("ProductObject #"+ productObjectId +" Not Found") );
        try {
            Cart_Item item = repository.findCart_ItemByCartAndProductObject(cart, productObject);
            item.setWantedQuantity(quantity);
            repository.save(new Cart_Item(productObject, cart, quantity));
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Cart_Item findByCartAndPO(Long cartId, Long productObjectId) {
        ProductObject productObject = productObjectService.findPOById(productObjectId).orElseThrow(()-> new NotFoundException("ProductObject #"+ productObjectId +" Not Found"));
        Cart cart = cartService.findCartById(cartId).orElseThrow(()-> new NotFoundException("Cart #"+ cartId +" Not Found"));
        return repository.findCart_ItemByCartAndProductObject(cart,productObject);
    }

    @Override
    public List<Cart_Item> findCart_ItemsByCart(Cart cart) {
        return repository.findCart_ItemsByCart(cart);
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
