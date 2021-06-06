package elyassgh.estore.api.Beans;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class Cart_Item {

    @EmbeddedId
    private Cart_Items_Key cartItem_Id;

    @ManyToOne
    @MapsId("productObjectID")
    @JoinColumn(name = "productObject_Id")
    private ProductObject productObject;

    @ManyToOne
    @JsonIgnore
    @MapsId("cartID")
    @JoinColumn(name = "cart_Id")
    private Cart cart;

    //Quantity Wanted --> ( Must Be Available! if not, the product will be disabled for purchase. )
    private Integer wantedQuantity;

    public Cart_Item() {
    }

    public Cart_Item(ProductObject productObject, Cart cart, Integer wanted_quantity) {
        this.productObject = productObject;
        this.cart = cart;
        this.wantedQuantity = wanted_quantity;
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

    public Integer getWantedQuantity() {
        return wantedQuantity;
    }

    public void setWantedQuantity(Integer wanted_quantity) {
        this.wantedQuantity = wanted_quantity;
    }
}
