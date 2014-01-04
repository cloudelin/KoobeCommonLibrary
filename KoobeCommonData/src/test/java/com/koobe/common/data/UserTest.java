package com.koobe.common.data;

import com.koobe.common.core.KoobeApplication;
import com.koobe.common.data.domain.User;
import com.koobe.common.data.repository.UserRepository;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by lyhcode on 2013/12/14.
 */
public class UserTest {

    private static KoobeApplication application;
    private static KoobeDataService service;
    private static UserRepository repo;

    public UserTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        application = KoobeApplication.getInstance();
        service = (KoobeDataService) application.getService(KoobeDataService.class);
        repo = (UserRepository) service.getRepository(UserRepository.class);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    @Test
    public void read() {
        User user = repo.findOne(70l);
        assertNotNull(user);
    }

    @Test
    public void auth() {
        List<User> users = repo.findByUserId("admin");
        assertNotNull(users);
        assert users.size() > 0;
        User admin = users.get(0);
        assertNotNull(admin);
        assert "admin".equals(admin.getPassword());
    }
}
