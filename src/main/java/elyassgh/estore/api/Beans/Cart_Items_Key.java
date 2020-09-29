package elyassgh.estore.api.Beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Cart_Items_Key implements Serializable {

    @Column(name = "productObject_Id")
    private Long productObjectID;

    @Column(name = "cart_Id")
    private Long cartID;

    public Cart_Items_Key() {
    }

    public Cart_Items_Key(Long productObjectID, Long cartID) {
        this.productObjectID = productObjectID;
        this.cartID = cartID;
    }

    public Long getProductObjectID() {
        return productObjectID;
    }

    public void setProductObjectID(Long productObject_ID) {
        this.productObjectID = productObject_ID;
    }

    public Long getCartID() {
        return cartID;
    }

    public void setCartID(Long cart_ID) {
        this.cartID = cart_ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart_Items_Key that = (Cart_Items_Key) o;
        return Objects.equals(productObjectID, that.productObjectID) &&
                Objects.equals(cartID, that.cartID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productObjectID, cartID);
    }

}
