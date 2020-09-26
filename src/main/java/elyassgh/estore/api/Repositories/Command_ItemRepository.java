package elyassgh.estore.api.Repositories;

import elyassgh.estore.api.Beans.Command;
import elyassgh.estore.api.Beans.Command_Item;
import elyassgh.estore.api.Beans.ProductObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Command_ItemRepository extends JpaRepository<Command_Item, Long> {

    // Find command items of a specific command
    List<Command_Item> findCommand_ItemsByCommand (Command command);

    // Find quantity commanded of a product object so far
    @Query(value = "SELECT SUM(cmd_quantity) FROM Command_Item WHERE productObject_Id = ?1", nativeQuery = true)
    Integer commandedQty (Long productObjId);

}
