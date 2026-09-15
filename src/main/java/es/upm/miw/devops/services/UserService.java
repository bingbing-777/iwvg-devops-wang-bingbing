package es.upm.miw.devops.services;

import es.upm.miw.devops.data.UserDatabase;
import es.upm.miw.devops.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDatabase userDatabase;

    public UserService(UserDatabase userDatabase){
        this.userDatabase = userDatabase;
    }

    public User findById(Long id) {
        return userDatabase.findById(id);
    }

    public List<User> findAll() {
        return userDatabase.findAll();
    }

    public List<User> findByBillable(Boolean billable) {
        return userDatabase.findByBillable(billable);
    }
}
