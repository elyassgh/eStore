package elyassgh.estore.api.Beans;

import javax.persistence.*;

@Entity
public class Command_Item {

    @EmbeddedId
    private Command_Items_Key cmd_Items_Id;

    @ManyToOne
    @MapsId("productObject_ID")
    @JoinColumn(name = "product_Id")
    private ProductObject productObject;

    @ManyToOne
    @MapsId("command_ID")
    @JoinColumn(name = "command_Id")
    private Command command;

    //Quantity Commanded --> (After checking availability and decreasing stock quantity !)
    private Integer cmd_quantity;

    public Command_Item() {
    }

    public Command_Item(ProductObject productObject, Command command, Integer cmd_quantity) {
        this.productObject = productObject;
        this.command = command;
        this.cmd_quantity = cmd_quantity;
    }

    public Command_Items_Key getCmd_Items_Id() {
        return cmd_Items_Id;
    }

    public void setCmd_Items_Id(Command_Items_Key cmd_Items_Id) {
        this.cmd_Items_Id = cmd_Items_Id;
    }

    public ProductObject getProductObject() {
        return productObject;
    }

    public void setProductObject(ProductObject productObject) {
        this.productObject = productObject;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public Integer getCmd_quantity() {
        return cmd_quantity;
    }

    public void setCmd_quantity(Integer cmd_quantity) {
        this.cmd_quantity = cmd_quantity;
    }
}
