package elyassgh.estore.api.wsRest;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import elyassgh.estore.api.Beans.User;
import elyassgh.estore.api.Services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/eStoreApi/user")
@Api("User Api Rest")
public class UserRest {

    @Autowired
    private UserService userService;

    @ApiOperation("create a new user")
    @PostMapping("/register")
    public int save(@RequestParam(name = "username") String username, @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password) {
        return userService.save(username, password, email);
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
    public Long userCount(@RequestParam(name = "from") Date start, @RequestParam(name = "to") Date end) {
        return userService.userCount(start, end);
    }

    @ApiOperation("delete a user")
    @DeleteMapping("/delete")
    public int delete(@RequestParam(name = "username") String username) {
        return userService.delete(userService.findUserByUsername(username));
    }
}
