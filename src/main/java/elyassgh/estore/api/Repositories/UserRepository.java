package elyassgh.estore.api.Repositories;

import elyassgh.estore.api.Beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find User by username
    User findUserByUsername (String username);

    // Find user by email
    User findUserByEmail (String email);

    // Count all users
    @Query("SELECT COUNT (u) FROM User u")
    Long userCount();

}
