package es.upm.miw.devops.functionaltests;

import es.upm.miw.devops.data.UserDatabase;
import es.upm.miw.devops.model.User;
import es.upm.miw.devops.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(new UserDatabase());
    }

    @Test
    void testFindByIdFound() {
        User user = userService.findById(1L);

        assertNotNull(user);
        assertEquals(1L, user.getId());
        assertEquals("Anna", user.getFirstName());
        assertEquals("Smith", user.getFamilyName());
        assertEquals("anna@gmail.com", user.getEmail());
        assertEquals("12345678A", user.getIdentity());
        assertEquals("Main Street 10", user.getAddress());
        assertEquals("Madrid", user.getCity());
        assertEquals("Madrid", user.getProvince());
        assertEquals("28001", user.getPostalCode());
    }

    @Test
    void testFindByIdNotFound() {
        User user = userService.findById(999L);

        assertNull(user);
    }

    @Test
    void testFindByBillableTrue() {
        List<User> users = userService.findByBillable(true);

        assertEquals(3, users.size());

        for (User user : users) {
            assertTrue(user.isBillable());
        }
    }

    @Test
    void testFindByBillableFalse() {
        List<User> users = userService.findByBillable(false);

        assertEquals(1, users.size());

        User user = users.get(0);

        assertEquals(4L, user.getId());
        assertEquals("Peter", user.getFirstName());
        assertFalse(user.isBillable());
    }

    @Test
    void testFindAll() {
        List<User> users = userService.findAll();

        assertEquals(4, users.size());
    }

    @Test
    void testDeleteByIdExistingUser() {
        userService.deleteById(2L);

        User user = userService.findById(2L);

        assertNull(user);
    }

    @Test
    void testDeleteByIdNonExistingUser() {
        userService.deleteById(999L);

        List<User> users = userService.findAll();

        assertEquals(4, users.size());
    }
}
