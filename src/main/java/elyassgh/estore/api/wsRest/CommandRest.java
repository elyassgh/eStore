package elyassgh.estore.api.wsRest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import elyassgh.estore.api.Beans.Command;
import elyassgh.estore.api.Beans.Command_Item;
import elyassgh.estore.api.Services.CommandService;
import elyassgh.estore.api.Services.Command_ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/eStoreApi/order")
@Api("Command Api Rest")

@CrossOrigin(origins = {"http://localhost"})
public class CommandRest {

    @Autowired
    private CommandService commandService;

    @Autowired
    private Command_ItemService command_itemService;

    @ApiOperation("create new command")
    @PostMapping("/create")
    public int save(@RequestParam(name = "cart") Long cartId, @RequestParam(name = "billAdr") String billingAdr,
            @RequestParam(name = "bilEmail") String billingEmail, @RequestParam(name = "shipAdr") String shippingAdr,
            @RequestParam(name = "shipFees") Double shippingFees) {
        return commandService.save(cartId, billingAdr, billingEmail, shippingAdr, shippingFees);
    }

    @ApiOperation("confirm a command")
    public void confirm(@RequestParam(name = "orderRef") String crf) {
        commandService.confirm(crf);
    }

    @ApiOperation("find an order by its reference")
    @GetMapping("/find")
    public Command findByCrf(@RequestParam(name = "orderRef") String crf) {
        return commandService.findByCrf(crf);
    }

    @ApiOperation("find all orders of a user")
    @GetMapping("/findAll/{id}")
    public List<Command> findCmdsOfUser(@PathVariable Long id) {
        return commandService.findCmdsOfUser(id);
    }

    @ApiOperation("find all orders between to dates")
    @GetMapping("/findAll/Between{start}And{end}")
    public List<Command> findCmdsBetween(@PathVariable Date start, @PathVariable Date end) {
        return commandService.findCmdsBetween(start, end);
    }

    @ApiOperation("find all orders of a billing adress")
    @GetMapping("/findAllByBillingAdr")
    public List<Command> findCmdsByBillAdr(@RequestParam(name = "billing_adr") String billing_adr) {
        return commandService.findCmdsByBillAdr(billing_adr);
    }

    @ApiOperation("find all orders of shipping adress")
    @GetMapping("/findAllByShippingAdr")
    public List<Command> findCmdsByShipAdr(@RequestParam(name = "shipping_adr") String shipping_adr) {
        return commandService.findCmdsByShipAdr(shipping_adr);
    }

    @ApiOperation("find all orders by status")
    @GetMapping("/findAllByStatus")
    public List<Command> findCmdsByStatus(@RequestParam(name = "status") String status) {
        return commandService.findCmdsByStatus(status);
    }

    @ApiOperation("find an order total")
    @GetMapping("/amount")
    public Double findCmdAmount(@RequestParam(name = "orderRef") String crf) {
        return commandService.findCmdAmount(crf);
    }

    @ApiOperation("find revenue of a period")
    @GetMapping("/revenue/{start}-{end}")
    public Double revenueOfPeriod(@PathVariable Date start, @PathVariable Date end,
            @RequestParam(name = "status") String status) {
        return commandService.revenueOfPeriod(start, end, status);
    }

    @ApiOperation("find revenue of a user so far")
    @GetMapping("/user/revenue")
    public Double revenueOfUser(@RequestParam(name = "username") String username) {
        return commandService.revenueOfUser(username);
    }

    @ApiOperation("find revenue of a user in a period")
    @GetMapping("/user/revenue/{start}-{end}")
    public Double revenueOfUser(@RequestParam(name = "username") String username, @PathVariable Date start,
            @PathVariable Date end) {
        return commandService.revenueOfUser(username, start, end);
    }

    @ApiOperation("find revenue of a user in a period by orders's status")
    @GetMapping("/user/revenue/{start}-{end}/{status}")
    public Double revenueOfUser(@RequestParam(name = "username") String username, @PathVariable Date start,
            @PathVariable Date end, @PathVariable String status) {
        return commandService.revenueOfUser(username, start, end, status);
    }

    @ApiOperation("find all orders billing emails")
    @GetMapping("/emails")
    public List<String> cmdEmailsList() {
        return commandService.cmdEmailsList();
    }

    @ApiOperation("find all orders billing emails in a period")
    @GetMapping("/emails/{start}-{end}/{status}")
    public List<String> cmdEmailsListOfPeriod(@PathVariable Date start, @PathVariable Date end,
            @PathVariable String status) {
        return commandService.cmdEmailsListOfPeriod(start, end, status);
    }

    @ApiOperation("delete an order")
    @DeleteMapping("/delete")
    public int delete(@RequestParam(name = "orderRef") String crf) {
        return commandService.delete(commandService.findByCrf(crf));
    }

    @ApiOperation("find all order's items")
    @GetMapping("/items")
    public List<Command_Item> findCmdItemsOfCmd(@RequestParam(name = "orderRef") String crf) {
        Command command = commandService.findByCrf(crf);
        return command_itemService.findCmdItemsOfCmd(command);
    }

    @ApiOperation("find ordered quantity of product object")
    @GetMapping("/product/{productObjId}/qty")
    public Integer commandedQtyOfPO(@PathVariable Long productObjId) {
        return command_itemService.commandedQtyOfPO(productObjId);
    }

    @ApiOperation("find ordered quantity of product object by commands status")
    @GetMapping("/product/{productObjId}/qty/{status}-commands")
    public Integer commandedQtyOfPO(@PathVariable Long productObjId, @PathVariable String status) {
        return command_itemService.commandedQtyOfPO(productObjId, status);
    }

    @ApiOperation("find ordered quantity of product object by orders's status in a period")
    @GetMapping("/product/{productObjId}/qty/{status}-commands/{start}-{end}")
    public Integer commandedQtyOfPO(@PathVariable Long productObjId, @PathVariable String status,
            @PathVariable Date start, @PathVariable Date end) {
        return command_itemService.commandedQtyOfPO(productObjId, status, start, end);
    }

    @ApiOperation("find ordered quantity of product object in an order")
    @GetMapping("/items/{productObjId}/qty")
    public Integer commandedQtyOfPOInCmd(@PathVariable Long productObjId, @RequestParam(name = "orderRef") String crf) {
        return command_itemService.commandedQtyOfPOInCmd(productObjId, crf);
    }

    // Could be used for order canceling
    @ApiOperation("delete an item in an order !!")
    @DeleteMapping("/items/delete")
    public int delete(@RequestParam(name = "poId") Long productObjId, @RequestParam(name = "orderRef") String crf) {
        Command_Item command_item = command_itemService.findByCmdAndPO(crf, productObjId);
        return command_itemService.delete(command_item);
    }

    // Useless end point !!?!
    @ApiOperation("delete all items of an order !!")
    @DeleteMapping("/items/deleteAll")
    public int deleteBatch(@RequestParam(name = "orderRef") String crf) {
        Command command = Optional.ofNullable(commandService.findByCrf(crf))
                .orElseThrow(() -> new RuntimeException("Command Not Found!"));
        List<Command_Item> items = command_itemService.findCmdItemsOfCmd(command);
        return command_itemService.deleteBatch(items);
    }
}
