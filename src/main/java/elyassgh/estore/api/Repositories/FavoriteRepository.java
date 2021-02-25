package elyassgh.estore.api.Repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import elyassgh.estore.api.Beans.Favorite;
import elyassgh.estore.api.Beans.ProductObject;
import elyassgh.estore.api.Beans.User;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    // Find favorites of a specific user
    List<Favorite> findFavoritesByUser (User user);

    // Find the most favorite product object
    Integer countFavoritesByProductObject (ProductObject productObject);

    // Find non available favorite (product objects)
    List<Favorite> findFavoritesByIsAvailableFalse ();

    // Find added favorites after a specific date
    List<Favorite> findFavoritesByAddedAtAfter (Date date);

    // Find added favorites by availability after a specific date
    List<Favorite> findFavoritesByIsAvailableAndAddedAtAfter (Boolean availability, Date date);

}
