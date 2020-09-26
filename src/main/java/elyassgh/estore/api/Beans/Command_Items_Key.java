package elyassgh.estore.api.Beans;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Command_Items_Key implements Serializable {

    @Column(name = "productObject_Id")
    Long productObject_ID;

    @Column(name = "command_Id")
    Long command_ID;

    public Command_Items_Key() {
    }

    public Command_Items_Key(Long productObject_ID, Long command_ID) {
        this.productObject_ID = productObject_ID;
        this.command_ID = command_ID;
    }

    public Long getProductObject_ID() {
        return productObject_ID;
    }

    public void setProductObject_ID(Long productObject_ID) {
        this.productObject_ID = productObject_ID;
    }

    public Long getCommand_ID() {
        return command_ID;
    }

    public void setCommand_ID(Long command_ID) {
        this.command_ID = command_ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Command_Items_Key that = (Command_Items_Key) o;
        return Objects.equals(productObject_ID, that.productObject_ID) &&
                Objects.equals(command_ID, that.command_ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productObject_ID, command_ID);
    }
}
