package elyassgh.estore.api.ServicesImpl;

import elyassgh.estore.api.Beans.Favorite;
import elyassgh.estore.api.Beans.ProductObject;
import elyassgh.estore.api.Beans.User;
import elyassgh.estore.api.Repositories.FavoriteRepository;
import elyassgh.estore.api.Services.FavoriteService;
import elyassgh.estore.api.Services.ProductObjectService;
import elyassgh.estore.api.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    public FavoriteRepository repository;
    @Autowired
    public UserService userService;
    @Autowired
    public ProductObjectService productObjectService;

    @Override
    public int save(Long userId, Long productObjectId) {
        try {
            User user = userService.findById(userId).orElseThrow(()-> new RuntimeException("User Not Found !"));
            ProductObject productObject = productObjectService.findPOById(productObjectId).orElseThrow(()-> new RuntimeException("ProductObject Not Found !"));
            Favorite favorite = new Favorite(user , productObject);
            repository.save(favorite);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Optional<Favorite> findById(Long favoriteId) {
        return repository.findById(favoriteId);
    }

    @Override
    public List<Favorite> findFavsOfUser(Long userId) {
        User user = userService.findById(userId).orElseThrow(()-> new RuntimeException("User Not Found !"));
        return repository.findFavoritesByUser(user);
    }

    @Override
    public Integer countFavsByPO(Long productObjectId) {
        ProductObject productObject = productObjectService.findPOById(productObjectId).orElseThrow(()-> new RuntimeException("ProductObject Not Found !"));
        return repository.countFavoritesByProductObject(productObject);
    }

    @Override
    public List<Favorite> findFavsNonAvailable() {
        return repository.findFavoritesByIsAvailableFalse();
    }

    @Override
    public List<Favorite> findFavsAddedAfter(Date date) {
        return repository.findFavoritesByAddedAtAfter(date);
    }

    @Override
    public List<Favorite> findFavsAddedAfter(Boolean availability, Date date) {
        return repository.findFavoritesByIsAvailableAndAddedAtAfter(availability, date);
    }

    @Override
    public int delete(Favorite favorite) {
        try {
            repository.delete(favorite);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
