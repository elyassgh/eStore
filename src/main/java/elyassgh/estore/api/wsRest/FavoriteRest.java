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

@RestController
@RequestMapping("/eStoreApi/favorite")
public class FavoriteRest {

    @Autowired
    FavoriteService favoriteService;

    @PostMapping("/add")
    public int save(
            @RequestParam(name = "userId") Long userId,
            @RequestParam(name = "poId") Long productObjectId) {
        return favoriteService.save(userId, productObjectId);
    }

    @GetMapping("/favorites")
    public List<Favorite> findFavsOfUser(@RequestParam(name = "userId") Long userId) {
        return favoriteService.findFavsOfUser(userId);
    }

    @GetMapping("/count")
    public Integer countFavsByPO(@RequestParam(name = "poId") Long productObjectId) {
        return favoriteService.countFavsByPO(productObjectId);
    }

    @GetMapping("/favorites/notavailable")
    public List<Favorite> findFavsNonAvailable() {
        return favoriteService.findFavsNonAvailable();
    }

    @GetMapping("/favorites/after/{date}")
    public List<Favorite> findFavsAddedAfter(@PathVariable Date date) {
        return favoriteService.findFavsAddedAfter(date);
    }

    @GetMapping("/favorites/available/after/{date}")
    public List<Favorite> availableAddedAfter(@PathVariable Date date) {
        return favoriteService.findFavsAddedAfter(true, date);
    }

    @GetMapping("/favorites/notavailable/after/{date}")
    public List<Favorite> notAvailableAddedAfter(@PathVariable Date date) {
        return favoriteService.findFavsAddedAfter(false, date);
    }

    @DeleteMapping("/delete")
    public int delete(@RequestParam(name = "favId") Long favoriteId) {
        Favorite favorite = favoriteService.findById(favoriteId).orElseThrow(()-> new RuntimeException("Error"));
        return favoriteService.delete(favorite);
    }
    
}
