package elyassgh.estore.api.Repositories;

import elyassgh.estore.api.Beans.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    // To do later ! IMPORTANT --> Remember it's the important thing you will learn in this project
    // 27/09/2020 21:45

}
