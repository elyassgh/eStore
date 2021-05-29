package elyassgh.estore.api.ServicesImpl;

import java.util.Date;
import java.util.Optional;

import elyassgh.estore.api.Beans.Role;
import elyassgh.estore.api.Exception.classes.NotFoundException;
import elyassgh.estore.api.Exception.classes.UnauthorizedException;
import elyassgh.estore.api.Security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import elyassgh.estore.api.Beans.User;
import elyassgh.estore.api.Repositories.UserRepository;
import elyassgh.estore.api.Services.CartService;
import elyassgh.estore.api.Services.UserService;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserRepository repository;

    @Autowired
    public CartService cartService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String login(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username, repository.findUserByUsername(username).getRoles());
        } catch (AuthenticationException e) {
            throw new UnauthorizedException("Invalid username/password supplied");
        }
    }

    @Override
    public User whoami(HttpServletRequest req) {
        return repository.findUserByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
    }

    @Override
    public String refresh(String username) {

        Optional<User> user = Optional.ofNullable(repository.findUserByUsername(username));

        if(user.isEmpty()) {
            throw new NotFoundException("User `"+ username + "` Not found" );
        }

        if (!user.get().getRoles().contains(Role.ROLE_ADMIN)) {
            throw new AccessDeniedException("Access denied, Admins Only.");
        }

        return jwtTokenProvider.createToken(username, user.get().getRoles());

    }

    @Override
    public int save(String username, String password, String email, Boolean isAdmin) {
        try {
            User user = new User(username, password, email);
            cartService.save(user);
            user.setPassword(encoder.encode(user.getPassword()));
            if(isAdmin) user.getRoles().add(Role.ROLE_ADMIN);
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
