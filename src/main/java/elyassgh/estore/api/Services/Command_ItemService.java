package elyassgh.estore.api.Services;

import java.util.Date;
import java.util.List;

import elyassgh.estore.api.Beans.Command;
import elyassgh.estore.api.Beans.Command_Item;

public interface Command_ItemService {

    // ***  Create & Update Services  ***
    public void save(Command_Item item);

    // ***  Read Services  ***
    public Command_Item findByCmdAndPO(String crf, Long productObjectId);
    public List<Command_Item> findCmdItemsOfCmd (Command command);
    public Integer commandedQtyOfPO (Long productObjId);
    public Integer commandedQtyOfPO (Long productObjId , String cmd_status);
    public Integer commandedQtyOfPO (Long productObjId , String cmd_status , Date start, Date end);
    public Integer commandedQtyOfPOInCmd (Long productObjId, String crf);

    // ***  Delete Services  ***
    public int delete (Command_Item command_item);
    public int deleteBatch (List<Command_Item> items);

}
