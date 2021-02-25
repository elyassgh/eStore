package elyassgh.estore.api.wsRest;

import elyassgh.estore.api.Beans.Command;
import elyassgh.estore.api.Beans.Command_Item;
import elyassgh.estore.api.Services.CommandService;
import elyassgh.estore.api.Services.Command_ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/eStoreApi/order")
public class CommandRest {

    @Autowired
    public CommandService commandService;

    @Autowired
    public Command_ItemService command_itemService;

    @PostMapping("/create")
    public int save(@RequestBody Command command) {
        return commandService.save(command);
    }

    @GetMapping("/find")
    public Command findByCrf(@RequestParam(name = "orderRef") String crf) {
        return commandService.findByCrf(crf);
    }

    @GetMapping("/findAll/{id}")
    public List<Command> findCmdsOfUser(@PathVariable Long id) {
        return commandService.findCmdsOfUser(id);
    }

    @GetMapping("/findAll/Between{start}And{end}")
    public List<Command> findCmdsBetween(@PathVariable Date start, @PathVariable Date end) {
        return commandService.findCmdsBetween(start, end);
    }

    @GetMapping("/findAll")
    public List<Command> findCmdsByBillAdr(@RequestParam(name = "billing_adr") String billing_adr) {
        return commandService.findCmdsByBillAdr(billing_adr);
    }

    @GetMapping("/findAll")
    public List<Command> findCmdsByShipAdr(@RequestParam(name = "shipping_adr") String shipping_adr) {
        return commandService.findCmdsByShipAdr(shipping_adr);
    }

    @GetMapping("/findAllByStatus")
    public List<Command> findCmdsByStatus(@RequestParam(name = "status") String status) {
        return commandService.findCmdsByStatus(status);
    }

    @GetMapping("/amount")
    public Double findCmdAmount(@RequestParam(name = "orderRef") String crf) {
        return commandService.findCmdAmount(crf);
    }

    @GetMapping("/revenue/{start}-{end}")
    public Double revenueOfPeriod(@PathVariable Date start, @PathVariable Date end,
                                  @RequestParam(name = "status") String status) {
        return commandService.revenueOfPeriod(start, end, status);
    }

    @GetMapping("/user/revenue")
    public Double revenueOfUser(@RequestParam(name = "username") String username) {
        return commandService.revenueOfUser(username);
    }

    @GetMapping("/user/revenue/{start}-{end}")
    public Double revenueOfUser(@RequestParam(name = "username") String username,
                                @PathVariable Date start,@PathVariable Date end) {
        return commandService.revenueOfUser(username, start, end);
    }

    @GetMapping("/user/revenue/{start}-{end}/#{status}")
    public Double revenueOfUser(@RequestParam(name = "username") String username,
                                @PathVariable Date start, @PathVariable Date end,
                                @PathVariable String status) {
        return commandService.revenueOfUser(username, start, end, status);
    }

    @GetMapping("/emails")
    public List<String> cmdEmailsList() {
        return commandService.cmdEmailsList();
    }

    @GetMapping("/emails/{start}-{end}/#{status}")
    public List<String> cmdEmailsListOfPeriod(@PathVariable Date start, @PathVariable Date end,
                                              @PathVariable String status) {
        return commandService.cmdEmailsListOfPeriod(start, end, status);
    }

    @DeleteMapping("/delete")
    public int delete(@RequestParam(name = "orderRef") String crf) {
        return commandService.delete(commandService.findByCrf(crf));
    }


    @GetMapping("/items")
    public List<Command_Item> findCmdItemsOfCmd(@RequestParam(name = "orderRef") String crf) {
        Command command = commandService.findByCrf(crf);
        return command_itemService.findCmdItemsOfCmd(command);
    }

    @PostMapping("/items/add")
    public int addItem(@RequestParam(name = "poId") Long productObjectId,
                       @RequestParam(name = "crf") String crf,
                       @RequestParam(name = "qty") Integer quantity)
    {
        return command_itemService.save(productObjectId, crf, quantity);
    }

    @GetMapping("/product/{productObjId}/qty")
    public Integer commandedQtyOfPO(@PathVariable Long productObjId) {
        return command_itemService.commandedQtyOfPO(productObjId);
    }

    @GetMapping("/product/{productObjId}/qty/{status}-commands")
    public Integer commandedQtyOfPO(@PathVariable Long productObjId, @PathVariable String status) {
        return command_itemService.commandedQtyOfPO(productObjId, status);
    }

    @GetMapping("/product/{productObjId}/qty/{status}-commands/{start}-{end}")
    public Integer commandedQtyOfPO(@PathVariable Long productObjId, @PathVariable String status,
                                    @PathVariable Date start, @PathVariable Date end) {
        return command_itemService.commandedQtyOfPO(productObjId, status, start, end);
    }

    @GetMapping("/items/{productObjId}/qty")
    public Integer commandedQtyOfPOInCmd(@PathVariable Long productObjId,
                                         @RequestParam(name = "orderRef") String crf) {
        return command_itemService.commandedQtyOfPOInCmd(productObjId, crf);
    }

    // Could be used for order canceling
    @DeleteMapping("/items/delete")
    public int delete(@RequestParam(name = "poId") Long productObjId,
                      @RequestParam(name = "orderRef") String crf) {
        Command_Item command_item = command_itemService.findByCmdAndPO(crf, productObjId);
        return command_itemService.delete(command_item);
    }

    // Useless end point !!?!
    @DeleteMapping("/items/deleteAll")
    public int deleteBatch(@RequestParam(name = "orderRef") String crf) {
        Command command = Optional.ofNullable(commandService.findByCrf(crf)).orElseThrow(() -> new RuntimeException("Command Not Found!"));
        List<Command_Item> items = command_itemService.findCmdItemsOfCmd(command);
        return command_itemService.deleteBatch(items);
    }
}
