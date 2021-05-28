package elyassgh.estore.api.Services;

import elyassgh.estore.api.Beans.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

public interface UserService {

    // Authentication methods
    public String signin(String username, String password);
    public User whoami(HttpServletRequest req);
    public String refresh(String username);

    // ***  Create & Update Services  ***
    public int save (String username, String password, String email, Boolean isAdmin);

    // ***  Read Services  ***
    public Optional<User> findById (Long id);
    public User findUserByUsername (String username);
    public User findUserByEmail (String email);
    public Long userCount ();
    public Long userCount (Date start, Date end);

    // ***  Delete Services  ***
    public int delete (User user);

}
