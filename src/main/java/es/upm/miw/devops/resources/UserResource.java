package es.upm.miw.devops.resources;

import es.upm.miw.devops.model.User;
import es.upm.miw.devops.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id){
        return userService.findById(id);
    }

    @GetMapping("/user")
    public List<User> findUsers(
            @RequestParam(required = false) Boolean active,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Boolean billable) {

        return userService.findByFilters(active, city, billable);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @PutMapping("/user/{id}/active")
    public void updateActive(@PathVariable Long id) {
        userService.updateActive(id);
    }

    @PutMapping("/user/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody User user) {
        userService.updateUser(id, user);
    }

    @PatchMapping("/user")
    public void updateUsersActive(@RequestBody List<User> users) {
        userService.updateUsersActive(users);
    }

}
