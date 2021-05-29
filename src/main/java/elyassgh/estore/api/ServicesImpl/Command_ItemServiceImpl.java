package elyassgh.estore.api.ServicesImpl;

import elyassgh.estore.api.Beans.*;
import elyassgh.estore.api.Exception.classes.NotFoundException;
import elyassgh.estore.api.Repositories.Command_ItemRepository;
import elyassgh.estore.api.Services.CommandService;
import elyassgh.estore.api.Services.Command_ItemService;
import elyassgh.estore.api.Services.ProductObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class Command_ItemServiceImpl implements Command_ItemService {

    @Autowired
    public Command_ItemRepository repository;

    @Autowired
    public ProductObjectService productObjectService;

    @Autowired
    public CommandService commandService;

    @Override
    public void save(Command_Item item) {
        repository.save(item);
    }

    @Override
    public Command_Item findByCmdAndPO(String crf, Long productObjectId) {
        ProductObject productObject = productObjectService.findPOById(productObjectId).orElseThrow(()-> new NotFoundException("ProductObject #"+ productObjectId +" Not Found") );
        Command command = commandService.findByCrf(crf);
        if (command == null) throw new NotFoundException("Command #"+ crf +" Not Found");
        return repository.findByCommandAndProductObject(command, productObject);
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
    public int delete(Command_Item command_item) {
        try {
            repository.delete(command_item);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int deleteBatch(List<Command_Item> items) {
        try {
            repository.deleteInBatch(items);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
