package elyassgh.estore.api.ServicesImpl;

import elyassgh.estore.api.Beans.Command;
import elyassgh.estore.api.Beans.Command_Item;
import elyassgh.estore.api.Repositories.Command_ItemRepository;
import elyassgh.estore.api.Services.Command_ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class Command_ItemServiceImpl implements Command_ItemService {

    @Autowired
    public Command_ItemRepository repository;

    @Override
    public int save(Command_Item command_item) {
        try {
            repository.save(command_item);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<Command_Item> findCmdItemsOfCmd(Command command) {
        return repository.findCommand_ItemsByCommand(command);
    }

    @Override
    public Integer commandedQtyOfPO(Long productObjId) {
        return repository.commandedQtyOfPO(productObjId);
    }

    @Override
    public Integer commandedQtyOfPO(Long productObjId, String cmd_status) {
        return repository.commandedQtyOfPO(productObjId, cmd_status);
    }

    @Override
    public Integer commandedQtyOfPO(Long productObjId, String cmd_status, Date start, Date end) {
        return repository.commandedQtyOfPO(productObjId, cmd_status, start, end);
    }

    @Override
    public Integer commandedQtyOfPOInCmd(Long productObjId, String crf) {
        return repository.commandedQtyOfPOInCmd(productObjId, crf);
    }

    @Override
    public int update(Command_Item command_item) {
        try {


            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int delete(Command_Item command_item) {
        return 0;
    }
}
