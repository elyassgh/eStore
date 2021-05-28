package elyassgh.estore.api.Repositories;

import elyassgh.estore.api.Beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by username
    User findUserByUsername (String username);

    // Find user by email
    User findUserByEmail (String email);

    // Count all users
    @Query("SELECT COUNT (u) FROM User u")
    Long userCount();

    // Count users joined in a specific period
    @Query("SELECT COUNT (u) FROM User u WHERE u.addedAt BETWEEN ?1 AND ?2")
    Long userCount(Date start, Date end);

    // Test the existence of a user
    boolean existsByUsername(String username);


}
