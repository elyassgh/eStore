package elyassgh.estore.api.Beans;

import javax.persistence.*;

@Entity
public class Cart_Item {

    @EmbeddedId
    private Cart_Items_Key cartItem_Id;

    @ManyToOne
    @MapsId("productObject_ID")
    @JoinColumn(name = "product_Id")
    private ProductObject productObject;

    @ManyToOne
    @MapsId("cart_ID")
    @JoinColumn(name = "cart_Id")
    private Cart cart;

    //Quantity Wanted --> ( Must Be Available! if not, the product will be disabled for purchase. )
    private Integer wanted_quantity;

    public Cart_Item() {
    }

    public Cart_Item(Cart_Items_Key cartItem_Id, ProductObject productObject, Cart cart, Integer wanted_quantity) {
        this.cartItem_Id = cartItem_Id;
        this.productObject = productObject;
        this.cart = cart;
        this.wanted_quantity = wanted_quantity;
    }

    public Cart_Items_Key getCartItem_Id() {
        return cartItem_Id;
    }

    public void setCartItem_Id(Cart_Items_Key cartItem_Id) {
        this.cartItem_Id = cartItem_Id;
    }

    public ProductObject getProductObject() {
        return productObject;
    }

    public void setProductObject(ProductObject productObject) {
        this.productObject = productObject;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Integer getWanted_quantity() {
        return wanted_quantity;
    }

    public void setWanted_quantity(Integer wanted_quantity) {
        this.wanted_quantity = wanted_quantity;
    }
}
