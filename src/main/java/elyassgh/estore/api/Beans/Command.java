package elyassgh.estore.api.Beans;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
public class Command {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "command_Id")
    private Long id;

    // Command Reference
    private String crf;

    // Command date ( important ! for database (ORM) queries )
    private Date date;

    // Command Confirmation timestamps
    private LocalDateTime timestamps;

    // Billing Address
    private String billingAdr;

    // Billing Email
    private String billingEmail;

    // Shipping Address
    private String shippingAdr;

    // Shipping fees
    private Double shippingFees;

    // Command Total price
    private Double amount;

    // Command number of items
    private Integer cardinal;

    // Command Status ( In processes, Sent, Shipped Or Canceled )
    private String cmdStatus;

    @ManyToOne
    @JoinColumn(name = "user_Id")
    private User user;

    @OneToMany(mappedBy = "command")
    private List<Command_Item> items;

    public Command() {
    }

    public Command(Long id, String crf, Date date, LocalDateTime timestamps , String billingAdr,
                   String billingEmail, String shippingAdr, Double shippingFees,
                   Double amount, Integer cardinal, String cmdStatus, User user,
                   List<Command_Item> items) {
        this.id = id;
        this.crf = crf;
        this.date = date;
        this.timestamps = timestamps;
        this.billingAdr = billingAdr;
        this.billingEmail = billingEmail;
        this.shippingAdr = shippingAdr;
        this.shippingFees = shippingFees;
        this.amount = amount;
        this.cardinal = cardinal;
        this.cmdStatus = cmdStatus;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalDateTime getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(LocalDateTime timestamps) {
        this.timestamps = timestamps;
    }

    public String getBillingAdr() {
        return billingAdr;
    }

    public void setBillingAdr(String billing_adr) {
        this.billingAdr = billing_adr;
    }

    public String getBillingEmail() {
        return billingEmail;
    }

    public void setBillingEmail(String billing_email) {
        this.billingEmail = billing_email;
    }

    public String getShippingAdr() {
        return shippingAdr;
    }

    public void setShippingAdr(String shipping_adr) {
        this.shippingAdr = shipping_adr;
    }

    public Double getShippingFees() {
        return shippingFees;
    }

    public void setShippingFees(Double shipping_fees) {
        this.shippingFees = shipping_fees;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getCardinal() {
        return cardinal;
    }

    public void setCardinal(Integer cardinal) {
        this.cardinal = cardinal;
    }

    public String getCmdStatus() {
        return cmdStatus;
    }

    public void setCmdStatus(String cmd_status) {
        this.cmdStatus = cmd_status;
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
