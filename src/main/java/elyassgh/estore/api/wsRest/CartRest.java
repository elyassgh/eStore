package elyassgh.estore.api.wsRest;

import elyassgh.estore.api.Beans.Cart;
import elyassgh.estore.api.Beans.Cart_Item;
import elyassgh.estore.api.Services.CartService;
import elyassgh.estore.api.Services.Cart_ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eStoreApi/cart")
public class CartRest {
    
    @Autowired
    public CartService cartService;

    @Autowired
    public Cart_ItemService cart_itemService;

    @PostMapping("/")
    public int save(@RequestBody Cart cart) {
        return cartService.save(cart);
    }

    @GetMapping("/user")
    public Cart findCartByUser(@RequestParam(name = "userId") Long id) {
        return cartService.findCartByUser(id);
    }

    @GetMapping("/carts")
    public List<Cart> findCartsWithItemsMoreThan(@RequestParam(name = "nbr_items") Integer nbr_items) {
        return cartService.findCartsWithItemsMoreThan(nbr_items);
    }

    // IMPORTANT --> Not Recommended to use it :) Why? --> (Cart Represent a foreign key to User)
    @DeleteMapping("/delete")
    public int delete(@RequestParam(name = "cartId") Long cartId) {
        Cart cart = cartService.findCartById(cartId).orElseThrow(()-> new RuntimeException("Cart Not Found"));
        return cartService.delete(cart);
    }

    @GetMapping("/items")
    public List<Cart_Item> findCart_ItemsByCart(@RequestParam(name = "cartId") Long cartId) {
        Cart cart = cartService.findCartById(cartId).orElseThrow(()-> new RuntimeException("Cart Not Found"));
        return cart_itemService.findCart_ItemsByCart(cart);
    }

    @PostMapping("/items/add")
    public int addItem(@RequestParam(name = "poId") Long productObjectId,
                    @RequestParam(name = "cartId") Long cartId,
                    @RequestParam(name = "qty") Integer quantity)
    {
        return cart_itemService.save(productObjectId, cartId, quantity);
    }


    @PutMapping("/items/update")
    public int update(@RequestParam(name = "poId") Long productObjectId,
                      @RequestParam(name = "cartId") Long cartId,
                      @RequestParam(name = "qty") Integer quantity)
    {
        return cart_itemService.update(productObjectId, cartId, quantity);
    }

    @DeleteMapping("/items/delete")
    public int deleteItem(@RequestParam(name = "poId") Long productObjId,
                          @RequestParam(name = "cartId") Long cartId) {
        Cart_Item item = cart_itemService.findByCartAndPO(cartId, productObjId);
        return cart_itemService.delete(item);
    }

    @DeleteMapping("/items/deleteAll")
    public int deleteItems(@RequestParam(name = "cartId") Long cartId) {
        Cart cart = cartService.findCartById(cartId).orElseThrow(()-> new RuntimeException("Cart Not Found"));
        List<Cart_Item> items = cart_itemService.findCart_ItemsByCart(cart);
        return cart_itemService.deleteBatch(items);
    }
}
