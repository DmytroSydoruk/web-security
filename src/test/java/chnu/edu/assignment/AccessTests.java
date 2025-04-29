package chnu.edu.assignment;

/*
    @author     dmytro
    @project    IntelliJ IDEA
    @class      AccessTesting
    @version 1.0.0
    @since  29.04.2025 - 18.29
*/

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class AccessTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void beforeAll() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithAnonymousUser
    void whenAnonymousUserAccessIndexThenOk() throws Exception {
        mockMvc.perform(get("/index.html"))
                .andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    void whenAnonymousUserAccessProtectedPageThenUnauthorized() throws Exception {
        mockMvc.perform(get("/user.html"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "USER")
    void whenUserAccessUserPageThenOk() throws Exception {
        mockMvc.perform(get("/user.html"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void whenAdminAccessAdminPageThenOk() throws Exception {
        mockMvc.perform(get("/admin.html"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void whenUserAccessAdminPageThenForbidden() throws Exception {
        mockMvc.perform(get("/admin.html"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "SUPERADMIN")
    void whenSuperadminAccessAllPagesThenOk() throws Exception {
        mockMvc.perform(get("/user.html"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/admin.html"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/superuser.html"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void whenAdminAccessSuperadminPageThenForbidden() throws Exception {
        mockMvc.perform(get("/superuser.html"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "SUPERADMIN")
    void whenSuperadminAccessAdminOnlyPageThenForbidden() throws Exception {
        mockMvc.perform(get("/admin_only.html"))
                .andExpect(status().isForbidden());
    }
}
