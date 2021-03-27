package elyassgh.estore.api.ServicesImpl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import elyassgh.estore.api.Beans.User;
import elyassgh.estore.api.Repositories.UserRepository;
import elyassgh.estore.api.Services.CartService;
import elyassgh.estore.api.Services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserRepository repository;

    @Autowired
    public CartService cartService;

    // private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public int save(String username, String password, String email) {
        try {
            User user = new User(username, password, email);
            cartService.save(user);
           // user.setPassword(encoder.encode(user.getPassword()));
            repository.save(user);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id);
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
