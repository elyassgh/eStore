package elyassgh.estore.api.wsRest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import elyassgh.estore.api.Beans.Favorite;
import elyassgh.estore.api.Services.FavoriteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/eStoreApi/favorite")
@Api("Favorite Api Rest")
public class FavoriteRest {

    @Autowired
    private FavoriteService favoriteService;

    @PostMapping("/add")
    @ApiOperation("add an item to favorite")
    public int save(
            @RequestParam(name = "userId") Long userId,
            @RequestParam(name = "poId") Long productObjectId) {
        return favoriteService.save(userId, productObjectId);
    }

    @ApiOperation("get all favorite items")
    @GetMapping("/favorites")
    public List<Favorite> findFavsOfUser(@RequestParam(name = "userId") Long userId) {
        return favoriteService.findFavsOfUser(userId);
    }
    
    @ApiOperation("count all favorites containing a product object")
    @GetMapping("/count")
    public Integer countFavsByPO(@RequestParam(name = "poId") Long productObjectId) {
        return favoriteService.countFavsByPO(productObjectId);
    }

    @ApiOperation("get all none available favorite items")
    @GetMapping("/favorites/notavailable")
    public List<Favorite> findFavsNonAvailable() {
        return favoriteService.findFavsNonAvailable();
    }

    @ApiOperation("get all favorite items added after a date")
    @GetMapping("/favorites/after/{date}")
    public List<Favorite> findFavsAddedAfter(@PathVariable Date date) {
        return favoriteService.findFavsAddedAfter(date);
    }

    @ApiOperation("get all available favorite items added after a date")    
    @GetMapping("/favorites/available/after/{date}")
    public List<Favorite> availableAddedAfter(@PathVariable Date date) {
        return favoriteService.findFavsAddedAfter(true, date);
    }

    @ApiOperation("get all non available favorite items added after a date")    
    @GetMapping("/favorites/notavailable/after/{date}")
    public List<Favorite> notAvailableAddedAfter(@PathVariable Date date) {
        return favoriteService.findFavsAddedAfter(false, date);
    }

    @ApiOperation("delete a favorite item")    
    @DeleteMapping("/delete")
    public int delete(@RequestParam(name = "favId") Long favoriteId) {
        Favorite favorite = favoriteService.findById(favoriteId).orElseThrow(()-> new RuntimeException("Error"));
        return favoriteService.delete(favorite);
    }
    
}
