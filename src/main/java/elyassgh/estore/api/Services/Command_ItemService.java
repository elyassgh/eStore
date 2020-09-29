package elyassgh.estore.api.Services;

import elyassgh.estore.api.Beans.Command;
import elyassgh.estore.api.Beans.Command_Item;

import java.util.Date;
import java.util.List;

public interface Command_ItemService {

    // ***  Create Services  ***
    public int save (Command_Item command_item);

    // ***  Read Services  ***
    public List<Command_Item> findCmdItemsOfCmd (Command command);
    public Integer commandedQtyOfPO (Long productObjId);
    public Integer commandedQtyOfPO (Long productObjId , String cmd_status);
    public Integer commandedQtyOfPO (Long productObjId , String cmd_status , Date start, Date end);
    public Integer commandedQtyOfPOInCmd (Long productObjId, String crf);

    // ***  Update Services  ***<
    public int update (Command_Item command_item);

    // ***  Delete Services  ***
    public int delete (Command_Item command_item);

}
