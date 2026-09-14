package es.upm.miw.devops.data;

import es.upm.miw.devops.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDatabase {

    private final List<User> users;

    public UserDatabase() {

        users = new ArrayList<>();

        users.add(new User(
                1L,
                "Anna",
                "Smith",
                "anna@gmail.com",
                "12345678A",
                "Main Street 10",
                "Madrid",
                "Madrid",
                "28001"
        ));

        users.add(new User(
                2L,
                "Charles",
                "Brown",
                "charles@gmail.com",
                "87654321B",
                "Second Street 20",
                "Madrid",
                "Madrid",
                "28002"
        ));

        users.add(new User(
                3L,
                "Laura",
                "Johnson",
                "laura@gmail.com",
                "11223344C",
                "Third Street 30",
                "Barcelona",
                "Barcelona",
                "08001"
        ));
    }

    public User findById(Long id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }
}
