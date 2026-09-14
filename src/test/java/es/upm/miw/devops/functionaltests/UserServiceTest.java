package es.upm.miw.devops.functionaltests;

import es.upm.miw.devops.data.UserDatabase;
import es.upm.miw.devops.model.User;
import es.upm.miw.devops.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

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
}
