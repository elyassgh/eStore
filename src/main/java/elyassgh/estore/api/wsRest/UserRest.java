package elyassgh.estore.api.wsRest;

import elyassgh.estore.api.Beans.User;
import elyassgh.estore.api.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/eStoreApi/user")
public class UserRest {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public int save(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/")
    public User findUserByUsername(@RequestParam(name = "username") String username) {
        return userService.findUserByUsername(username);
    }

    @GetMapping("/")
    public User findUserByEmail(@RequestParam(name = "email") String email) {
        return userService.findUserByEmail(email);
    }

    @GetMapping("/count")
    public Long userCount() {
        return userService.userCount();
    }

    @GetMapping("/count")
    public Long userCount(@RequestParam(name = "start") Date start,@RequestParam(name = "end") Date end) {
        return userService.userCount(start, end);
    }

    @DeleteMapping("/delete")
    public int delete(@RequestParam(name = "username") String username) {
        return userService.delete(userService.findUserByUsername(username));
    }
}
