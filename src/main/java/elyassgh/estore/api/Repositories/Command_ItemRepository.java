package elyassgh.estore.api.Repositories;

import elyassgh.estore.api.Beans.Command;
import elyassgh.estore.api.Beans.Command_Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Command_ItemRepository extends JpaRepository<Command_Item, Long> {

    // Find command items of a specific command
    List<Command_Item> findCommand_ItemsByCommand (Command command);

    // Find quantity commanded of a product object so far
    @Query("SELECT SUM(ci.cmdQuantity) FROM Command_Item ci WHERE ci.productObject.id = ?1")
    Integer commandedQty (Long productObjId);

    // Find quantity commanded of a product object of a specific command (ref)
    @Query("SELECT SUM(ci.cmdQuantity) FROM Command_Item ci WHERE ci.productObject.id = ?1 AND ci.command.crf = ?2 ")
    Integer commandedQty (Long productObjId, Long crf);

}
