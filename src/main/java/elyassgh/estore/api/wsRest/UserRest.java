package elyassgh.estore.api.wsRest;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import elyassgh.estore.api.Beans.User;
import elyassgh.estore.api.Services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/eStoreApi/user")
@Api("User Api Rest")

@CrossOrigin(origins = {"http://localhost"})
public class UserRest {

    @Autowired
    private UserService userService;

    @ApiOperation("create a new user")
    @PostMapping("/register")
    public int save(@RequestParam(name = "username") String username, @RequestParam(name = "email") String email,
                    @RequestParam(name = "password") String password,
                    @RequestParam(name = "isAdmin") Boolean isAdmin) {
        return userService.save(username, password, email, isAdmin);
    }

    @ApiOperation("find a certain user")
    @GetMapping("/find")
    public User findUserByUsername(@RequestParam(name = "username") String username) {
        return userService.findUserByUsername(username);
    }

    @ApiOperation("find a user by email")
    @GetMapping("/findByEmail")
    public User findUserByEmail(@RequestParam(name = "email") String email) {
        return userService.findUserByEmail(email);
    }

    @ApiOperation("count all users")
    @GetMapping("/count")
    public Long userCount() {
        return userService.userCount();
    }

    @ApiOperation("count all users added in a periode")
    @GetMapping("/countUsers")
    public Long userCount(@RequestParam(name = "from") LocalDateTime start, @RequestParam(name = "to") LocalDateTime end) {
        return userService.userCount(start, end);
    }

    @ApiOperation("delete a user")
    @DeleteMapping("/delete")
    public int delete(@RequestParam(name = "username") String username) {
        User user = userService.findUserByUsername(username);
        if (user == null) return 0;
        userService.delete(user);
        return 1;
    }

    @ApiOperation("Login gateway")
    @PostMapping("/authenticate")
    public String login(@RequestParam(name = "username") String username,
                         @RequestParam(name = "password")String password) {
        return userService.login(username, password);
    }

    @ApiOperation("Return the authenticated user")
    @GetMapping(value = "/me")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User whoami(HttpServletRequest req) {
        return userService.whoami(req);
    }

}
