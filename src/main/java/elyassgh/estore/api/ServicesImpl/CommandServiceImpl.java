package elyassgh.estore.api.ServicesImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import elyassgh.estore.api.Beans.Cart;
import elyassgh.estore.api.Beans.Command;
import elyassgh.estore.api.Beans.Command_Item;
import elyassgh.estore.api.Beans.User;
import elyassgh.estore.api.Repositories.CommandRepository;
import elyassgh.estore.api.Services.CartService;
import elyassgh.estore.api.Services.CommandService;
import elyassgh.estore.api.Services.Command_ItemService;
import elyassgh.estore.api.Services.UserService;

@Service
public class CommandServiceImpl implements CommandService {

    @Autowired
    public CommandRepository repository;

    @Autowired
    public UserService userService;

    @Autowired
    public CartService cartService;

    @Autowired
    public Command_ItemService cmdItemService;

    private ZoneId zone = ZoneId.of("Casablanca/Africa");

    @Override
    public int save(Long cartId, String billingAdr, String billingEmail, String shippingAdr, Double shippingFees) {

        Cart cart = cartService.findCartById(cartId).orElseThrow(()-> new RuntimeException("Cart Not found"));

        String ref = LocalTime.now(zone).toString() + "/" + cart.getUser().getId().toString() + "/"
                + LocalDate.now(zone);

        Command c = new Command(ref, billingAdr, billingEmail, shippingAdr, shippingFees, cart.getAmount(), "status");

        c.setUser(cart.getUser());

        cart.getItems().forEach(
            item -> 
            cmdItemService.save(new Command_Item(item.getProductObject(), c, item.getWantedQuantity()))
        );
        try {
            repository.save(c);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public void confirm(String crf) {
        Command commande = repository.findByCrf(crf);
        commande.setTimestamps(LocalDateTime.now());
        repository.save(commande);
    }

    @Override
    public Command findByCrf(String crf) {
        return repository.findByCrf(crf);
    }

    @Override
    public List<Command> findCmdsOfUser(Long userId) {
        User user = userService.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));
        return repository.findCommandsByUser(user);
    }

    @Override
    public List<Command> findCmdsBetween(Date start, Date end) {
        return repository.findCommandsByDateBetween(start, end);
    }

    @Override
    public List<Command> findCmdsByBillAdr(String billing_adr) {
        return repository.findCommandsByBillingAdr(billing_adr);
    }

    @Override
    public List<Command> findCmdsByShipAdr(String shipping_adr) {
        return repository.findCommandsByShippingAdr(shipping_adr);
    }

    @Override
    public List<Command> findCmdsByStatus(String status) {
        return repository.findCommandsByCmdStatus(status);
    }

    @Override
    public Double findCmdAmount(String crf) {
        return repository.findCmdAmount(crf);
    }

    @Override
    public Double revenueOfPeriod(Date start, Date end, String status) {
        return repository.revenueOfPeriod(start, end, status);
    }

    @Override
    public Double revenueOfUser(String username) {
        return repository.revenueOfUser(username);
    }

    @Override
    public Double revenueOfUser(String username, Date start, Date end) {
        return repository.revenueOfUser(username, start, end);
    }

    @Override
    public Double revenueOfUser(String username, Date start, Date end, String status) {
        return repository.revenueOfUser(username, start, end, status);
    }

    @Override
    public List<String> cmdEmailsList() {
        return repository.cmdEmailsList();
    }

    @Override
    public List<String> cmdEmailsListOfPeriod(Date start, Date end, String status) {
        return repository.cmdEmailsListOfPeriod(start, end, status);
    }

    @Override
    public int delete(Command command) {
        try {
            repository.delete(command);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
