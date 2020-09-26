package elyassgh.estore.api.Repositories;

import elyassgh.estore.api.Beans.Command;
import elyassgh.estore.api.Beans.Command_Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Command_ItemRepository extends JpaRepository<Command_Item, Long> {

    // Find command items of a specific command
    List<Command_Item> findCommand_ItemsByCommand (Command command);

    // 
}
