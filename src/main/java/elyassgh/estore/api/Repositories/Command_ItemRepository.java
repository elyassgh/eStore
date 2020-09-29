package elyassgh.estore.api.Repositories;

import elyassgh.estore.api.Beans.Command;
import elyassgh.estore.api.Beans.Command_Item;
import elyassgh.estore.api.Beans.Command_Items_Key;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface Command_ItemRepository extends JpaRepository<Command_Item, Command_Items_Key> {

    // Find command items of a specific command
    List<Command_Item> findCommand_ItemsByCommand (Command command);

    // Find quantity commanded of a product object so far
    @Query("SELECT SUM(ci.cmdQuantity) FROM Command_Item ci WHERE ci.productObject.id = ?1")
    Integer commandedQtyOfPO (Long productObjId);

    // Find quantity commanded of a product object so far by status
    @Query("SELECT SUM(ci.cmdQuantity) FROM Command_Item ci WHERE ci.productObject.id = ?1 AND ci.command.cmdStatus = ?2")
    Integer commandedQtyOfPO (Long productObjId , String cmd_status);

    // Find quantity commanded of a product object in a period by status
    @Query("SELECT SUM(ci.cmdQuantity) FROM Command_Item ci WHERE ci.productObject.id = ?1 AND ci.command.cmdStatus = ?2 AND ci.command.date BETWEEN ?3 AND ?4")
    Integer commandedQtyOfPO (Long productObjId , String cmd_status , Date start, Date end);

    // Find quantity commanded of a product object in a specific command (ref)
    @Query("SELECT SUM(ci.cmdQuantity) FROM Command_Item ci WHERE ci.productObject.id = ?1 AND ci.command.crf = ?2 ")
    Integer commandedQtyOfPOInCmd (Long productObjId, String crf);

}
