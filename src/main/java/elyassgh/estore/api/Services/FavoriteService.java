package elyassgh.estore.api.Services;

import elyassgh.estore.api.Beans.Favorite;
import elyassgh.estore.api.Beans.ProductObject;
import elyassgh.estore.api.Beans.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface FavoriteService {

    // ***  Create & Update Services  ***
    public int save (Favorite favorite);

    // ***  Read Services  ***
    public Optional<Favorite> findById(Long favoriteId);
    public List<Favorite> findFavsOfUser (Long userId);
    public Integer countFavsByPO (Long productObjectId);
    public List<Favorite> findFavsNonAvailable ();
    public List<Favorite> findFavsAddedAfter (Date date);
    public List<Favorite> findFavsAddedAfter (Boolean availability, Date date);

    // ***  Delete Services  ***
    public int delete (Favorite favorite);

}
