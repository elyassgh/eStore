package elyassgh.estore.api.Services;

import elyassgh.estore.api.Beans.Command;
import elyassgh.estore.api.Beans.User;

import java.util.Date;
import java.util.List;

public interface CommandService {

    // ***  Create Services  ***
    public int save (Command command);

    // ***  Read Services  ***
    public Command findByCrf (String crf);
    public List<Command> findCmdsBetween (Date start, Date end);
    public List<Command> findCmdsByBillAdr (String billing_adr);
    public List<Command> findCmdsByShipAdr (String shipping_adr);
    public List<Command> findComdsByStatus (String status);
    public Double findCmdAmount (String crf);
    public Double revenueOfPeriod (Date start, Date end, String status);
    public Double revenueOfUser (User user);
    public Double revenueOfUser (User user, Date start, Date end);
    public Double revenueOfUser (User user, Date start, Date end, String status);
    public List<String> cmdEmailsList ();
    public List<String> cmdEmailsListOfPeriod (Date start, Date end, String status);

    // ***  Update Services  ***
    public int update (Command command);

    // ***  Delete Services  ***
    public int delete (Command command);

}
