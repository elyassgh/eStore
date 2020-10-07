package elyassgh.estore.api.ServicesImpl;

import elyassgh.estore.api.Beans.Command;
import elyassgh.estore.api.Beans.User;
import elyassgh.estore.api.Repositories.CommandRepository;
import elyassgh.estore.api.Services.CommandService;
import elyassgh.estore.api.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommandServiceImpl implements CommandService {

    @Autowired
    public CommandRepository repository;

    @Autowired
    public UserService userService;

    @Override
    public int save(Command command) {
        try {
            repository.save(command);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Command findByCrf(String crf) {
        return repository.findByCrf(crf);
    }

    @Override
    public List<Command> findCmdsOfUser(Long userId) {
        User user = userService.findById(userId).orElseThrow(()-> new RuntimeException("User Not Found"));
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
        return repository.findCommandsByShippingAdr(shipping_adr) ;
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
