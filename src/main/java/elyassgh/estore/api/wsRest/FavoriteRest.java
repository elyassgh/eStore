package elyassgh.estore.api.wsRest;

import elyassgh.estore.api.Services.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eStoreApi/favorite")
public class FavoriteRest {

    @Autowired
    FavoriteService favoriteService;




}
