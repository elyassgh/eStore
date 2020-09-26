package elyassgh.estore.api.Beans;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Command {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Command Reference
    private String crf;

    // Command date
    private LocalDateTime date;

    // Billing Address
    private String billing_adr;

    // Billing Email
    private String billing_email;

    // Shipping Address
    private String shipping_adr;

    // Shipping fees
    private Double shipping_fees;

    // Command Total price
    private Double amount;

    // Command number of items
    private Integer cardinal;

    // Command Status ( In processes, Sent, Shipped Or Canceled )
    private String cmd_status;

    @ManyToOne
    @JoinColumn(name = "user_Id")
    private User user;

    @OneToMany(mappedBy = "command")
    private List<Command_Item> items;

    public Command() {
    }

    public Command(Long id, String crf, LocalDateTime date, String billing_adr,
                   String billing_email, String shipping_adr, Double shipping_fees,
                   Double amount, Integer cardinal, String cmd_status, User user,
                   List<Command_Item> items) {
        this.id = id;
        this.crf = crf;
        this.date = date;
        this.billing_adr = billing_adr;
        this.billing_email = billing_email;
        this.shipping_adr = shipping_adr;
        this.shipping_fees = shipping_fees;
        this.amount = amount;
        this.cardinal = cardinal;
        this.cmd_status = cmd_status;
        this.user = user;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCrf() {
        return crf;
    }

    public void setCrf(String crf) {
        this.crf = crf;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getBilling_adr() {
        return billing_adr;
    }

    public void setBilling_adr(String billing_adr) {
        this.billing_adr = billing_adr;
    }

    public String getBilling_email() {
        return billing_email;
    }

    public void setBilling_email(String billing_email) {
        this.billing_email = billing_email;
    }

    public String getShipping_adr() {
        return shipping_adr;
    }

    public void setShipping_adr(String shipping_adr) {
        this.shipping_adr = shipping_adr;
    }

    public Double getShipping_fees() {
        return shipping_fees;
    }

    public void setShipping_fees(Double shipping_fees) {
        this.shipping_fees = shipping_fees;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCmd_status() {
        return cmd_status;
    }

    public void setCmd_status(String cmd_status) {
        this.cmd_status = cmd_status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Command_Item> getItems() {
        return items;
    }

    public void setItems(List<Command_Item> items) {
        this.items = items;
    }
}
