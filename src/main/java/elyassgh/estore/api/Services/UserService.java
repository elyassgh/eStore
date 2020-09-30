package elyassgh.estore.api.Services;

import elyassgh.estore.api.Beans.User;

import java.util.Date;

public interface UserService {

    // ***  Create & Update Services  ***
    public int save (User user);

    // ***  Read Services  ***
    public User findUserByUsername (String username);
    public User findUserByEmail (String email);
    public Long userCount ();
    public Long userCount (Date start, Date end);

    // ***  Delete Services  ***
    public int delete (User user);

}
