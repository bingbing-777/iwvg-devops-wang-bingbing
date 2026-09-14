package es.upm.miw.devops.functionaltests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
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
                .andExpect(status().isOk());
    }
}
