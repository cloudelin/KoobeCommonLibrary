package com.koobe.common.data;

import com.koobe.common.core.KoobeApplication;
import com.koobe.common.data.domain.Draft;
import com.koobe.common.data.domain.User;
import com.koobe.common.data.repository.UserRepository;
import org.junit.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by lyhcode on 2013/12/14.
 */
public class UserTest {

    private static KoobeApplication application;
    private static KoobeDataService service;
    private static UserRepository userRepository;

    public UserTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        application = KoobeApplication.getInstance();
        service = (KoobeDataService) application.getService(KoobeDataService.class);
        userRepository = (UserRepository) service.getRepository(UserRepository.class);
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
        User user = userRepository.findOne(70l);
        assertNotNull(user);
    }

    @Test
    public void auth() {
        User user = userRepository.getByUserIdAndPasswordAndOrgId("admin", "admin", 1);
        assertNotNull(user);
    }

    @Test
    public void drafts() {
        User user = userRepository.getByUserIdAndPasswordAndOrgId("admin", "admin", 1);

        assertNotNull(user.getDrafts());
        assert user.getDrafts().size() == 0;

        user.getDrafts().add(makeDraft());

        userRepository.save(user);
    }

    private Draft makeDraft() {
        Draft draft;
        draft = new Draft();

        draft.setName("Test a Draft Book");
        draft.setStatus(Draft.DraftStatus.CONVERTING);
        draft.setProgress(100);

        return draft;
    }

}
