package elyassgh.estore.api.Repositories;

import elyassgh.estore.api.Beans.Command;
import elyassgh.estore.api.Beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;

@Repository
public interface CommandRepository extends JpaRepository<Command, Long> {

    // Find a command by its reference
    Command findByCrf (String crf);

    // Find commands of a specific date
    List<Command> findCommandsByDateBetween (Date start, Date end);

    // Find commands of a specific user
    List<Command> findCommandsByUser (User user);

    // Find commands of a specific billing address
    List<Command> findCommandsByBillingAdr (String billing_adr);

    // Find commands of a specific shipping address
    List<Command> findCommandsByShippingAdr (String shipping_adr);

    // Find all commands having a specific status;
    List<Command> findCommandsByCmdStatus (String status);

    // Find total amount of a specific command
    @Query("SELECT c.amount FROM Command c WHERE c.crf = ?1")
    Double findCmdAmount (String crf);

    // Find Revenue of a specific period by Status;
    @Query("SELECT SUM(c.amount) FROM Command c WHERE (c.date BETWEEN ?1 AND ?2) AND c.cmdStatus LIKE ?3")
    Double revenueOfPeriod(Date start, Date end , String cmd_status);

    // Find Revenue of a specific user so far
    @Query("SELECT SUM(c.amount) FROM Command c WHERE c.user.username Like ?1")
    Double revenueOfUser (String Username);

    // Find Revenue of a specific user in a specific period
    @Query("SELECT SUM(c.amount) FROM Command c WHERE c.user.username Like ?1 AND (c.date BETWEEN ?2 AND ?3)")
    Double revenueOfUser (String Username ,Date start, Date end);

    // Find Revenue of a specific user in a specific period by status
    @Query("SELECT SUM(c.amount) FROM Command c WHERE c.user.username Like ?1 AND (c.date BETWEEN ?2 AND ?3) AND c.cmdStatus = ?4")
    Double revenueOfUser (String Username ,Date start, Date end, String cmd_status);

    // Find List of emails Having done a command so far
    @Query("SELECT DISTINCT c.billingEmail FROM Command c")
    List<String> cmdEmailsList ();

    // Find List of emails Having done a command in a specific period by status
    @Query("SELECT DISTINCT c.billingEmail FROM Command c WHERE (c.date BETWEEN ?1 AND ?2) AND c.cmdStatus = ?3")
    List<String> cmdEmailsListOfPeriod (Date start, Date end, String cmd_status);

}
