package elyassgh.estore.api.Beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Cart_Items_Key implements Serializable {

    @Column(name = "productObject_Id")
    Long productObject_ID;

    @Column(name = "cart_Id")
    Long cart_ID;

    public Cart_Items_Key() {
    }

    public Cart_Items_Key(Long productObject_ID, Long cart_ID) {
        this.productObject_ID = productObject_ID;
        this.cart_ID = cart_ID;
    }

    public Long getProductObject_ID() {
        return productObject_ID;
    }

    public void setProductObject_ID(Long productObject_ID) {
        this.productObject_ID = productObject_ID;
    }

    public Long getCart_ID() {
        return cart_ID;
    }

    public void setCart_ID(Long cart_ID) {
        this.cart_ID = cart_ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart_Items_Key that = (Cart_Items_Key) o;
        return Objects.equals(productObject_ID, that.productObject_ID) &&
                Objects.equals(cart_ID, that.cart_ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productObject_ID, cart_ID);
    }

}
