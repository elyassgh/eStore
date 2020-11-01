package elyassgh.estore.api.wsRest;

import elyassgh.estore.api.Beans.Favorite;
import elyassgh.estore.api.Services.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/eStoreApi/favorite")
public class FavoriteRest {

    @Autowired
    FavoriteService favoriteService;

    @PostMapping("/")
    public int save(Favorite favorite) {
        return favoriteService.save(favorite);
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
