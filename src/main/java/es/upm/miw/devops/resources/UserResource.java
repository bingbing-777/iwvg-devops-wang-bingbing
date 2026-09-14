package es.upm.miw.devops.resources;

import es.upm.miw.devops.model.User;
import es.upm.miw.devops.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
