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
                "28001",
                false
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
                "28002",
                false
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
                "08001",
                false
        ));

        users.add(new User(
                4L,
                "Peter",
                "White",
                "",
                "99887766D",
                "Fourth Street 40",
                "Madrid",
                "Madrid",
                "28003",
                true
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

    public List<User> findAll() {
        return users;
    }

    public List<User> findByBillable(Boolean billable) {
        List<User> result = new ArrayList<>();

        for (User user : users) {
            if (user.isBillable() == billable) {
                result.add(user);
            }
        }

        return result;
    }

    public void deleteById(Long id) {
        users.removeIf(user -> user.getId().equals(id));
    }

    public void updateActive(Long id) {
        User user = findById(id);

        if (user != null) {
            user.setActive(true);
        }
    }
}
