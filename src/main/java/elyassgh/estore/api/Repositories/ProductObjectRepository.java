package elyassgh.estore.api.Repositories;

import elyassgh.estore.api.Beans.ProductObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductObjectRepository extends JpaRepository<ProductObject,Long> {
}
