package es.upm.miw.devops.functionaltests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserResourceTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetUserFound() throws Exception {

        mockMvc.perform(get("/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Anna"))
                .andExpect(jsonPath("$.familyName").value("Smith"))
                .andExpect(jsonPath("$.email").value("anna@gmail.com"))
                .andExpect(jsonPath("$.identity").value("12345678A"))
                .andExpect(jsonPath("$.address").value("Main Street 10"))
                .andExpect(jsonPath("$.city").value("Madrid"))
                .andExpect(jsonPath("$.province").value("Madrid"))
                .andExpect(jsonPath("$.postalCode").value("28001"));
    }

    @Test
    void testGetUserNotFound() throws Exception {

        mockMvc.perform(get("/user/999"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void testFindUsersBillableTrue() throws Exception {

        mockMvc.perform(get("/user")
                        .param("active", "false")
                        .param("city", "Barcelona")
                        .param("billable", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(3))
                .andExpect(jsonPath("$[0].active").value(false))
                .andExpect(jsonPath("$[0].city").value("Barcelona"));
    }

    @Test
    void testFindUsersBillableFalse() throws Exception {

        mockMvc.perform(get("/user")
                        .param("active", "true")
                        .param("city", "Madrid")
                        .param("billable", "false"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(4))
                .andExpect(jsonPath("$[0].active").value(true))
                .andExpect(jsonPath("$[0].city").value("Madrid"));
    }

    @Test
    void testDeleteUserExistingUser() throws Exception {

        mockMvc.perform(delete("/user/2"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/user/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    void testDeleteUserNonExistingUser() throws Exception {

        mockMvc.perform(delete("/user/999"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(4));
    }

    @Test
    void testUpdateActive() throws Exception {
        mockMvc.perform(put("/user/2/active"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/user/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.active").value(true));
    }

    @Test
    void testUpdateActiveAlreadyActive() throws Exception {

        mockMvc.perform(get("/user/4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.active").value(true));

        mockMvc.perform(put("/user/4/active"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/user/4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.active").value(true));
    }

    @Test
    void testUpdateUserExistingUser() throws Exception {
        mockMvc.perform(put("/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "firstName": "Anna Updated",
                                "familyName": "Smith Updated",
                                "email": "anna.updated@gmail.com",
                                "identity": "99999999Z",
                                "address": "New Street 100",
                                "city": "Barcelona",
                                "province": "Barcelona",
                                "postalCode": "08002",
                                "active": true
                            }
                            """))
                .andExpect(status().isOk());

        mockMvc.perform(get("/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Anna Updated"))
                .andExpect(jsonPath("$.familyName").value("Smith Updated"))
                .andExpect(jsonPath("$.email").value("anna.updated@gmail.com"))
                .andExpect(jsonPath("$.identity").value("99999999Z"))
                .andExpect(jsonPath("$.address").value("New Street 100"))
                .andExpect(jsonPath("$.city").value("Barcelona"))
                .andExpect(jsonPath("$.province").value("Barcelona"))
                .andExpect(jsonPath("$.postalCode").value("08002"))
                .andExpect(jsonPath("$.active").value(true));
    }

    @Test
    void testUpdateUserNonExistingUser() throws Exception {
        mockMvc.perform(put("/user/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "firstName": "John",
                                "familyName": "Doe",
                                "email": "john@gmail.com",
                                "identity": "11111111A",
                                "address": "Unknown Street",
                                "city": "Madrid",
                                "province": "Madrid",
                                "postalCode": "28000",
                                "active": true
                            }
                            """))
                .andExpect(status().isOk());

        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(4));
    }
}
