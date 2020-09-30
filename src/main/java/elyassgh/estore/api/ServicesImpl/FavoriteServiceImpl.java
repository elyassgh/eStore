package elyassgh.estore.api.ServicesImpl;

import elyassgh.estore.api.Beans.Favorite;
import elyassgh.estore.api.Beans.ProductObject;
import elyassgh.estore.api.Beans.User;
import elyassgh.estore.api.Repositories.FavoriteRepository;
import elyassgh.estore.api.Services.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    public FavoriteRepository repository;

    @Override
    public int save(Favorite favorite) {
        try {
            repository.save(favorite);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<Favorite> findFavsOfUser(User user) {
        return repository.findFavoritesByUser(user);
    }

    @Override
    public Integer countFavsByPO(ProductObject productObject) {
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
