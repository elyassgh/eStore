package elyassgh.estore.api.ServicesImpl;

import elyassgh.estore.api.Beans.User;
import elyassgh.estore.api.Repositories.UserRepository;
import elyassgh.estore.api.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserRepository repository;

    @Override
    public int save(User user) {
        try {
            repository.save(user);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public User findUserByUsername(String username) {
        return repository.findUserByUsername(username);
    }

    @Override
    public User findUserByEmail(String email) {
        return repository.findUserByEmail(email);
    }

    @Override
    public Long userCount() {
        return repository.userCount();
    }

    @Override
    public Long userCount(Date start, Date end) {
        return repository.userCount(start, end);
    }

    @Override
    public int delete(User user) {
        try {
            repository.delete(user);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}