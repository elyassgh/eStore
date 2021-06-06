package elyassgh.estore.api.Services;

import elyassgh.estore.api.Beans.User;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface UserService {

    // Authentication methods
    public String login(String username, String password);
    public User whoami(HttpServletRequest req);
    public String refresh(String username);

    // ***  Create & Update Services  ***
    public int save (String username, String password, String email, Boolean isAdmin);

    // ***  Read Services  ***
    public Optional<User> findById (Long id);
    public User findUserByUsername (String username);
    public User findUserByEmail (String email);
    public Long userCount ();
    public Long userCount (LocalDateTime start, LocalDateTime end);

    // ***  Delete Services  ***
    public void delete (User user);

}
