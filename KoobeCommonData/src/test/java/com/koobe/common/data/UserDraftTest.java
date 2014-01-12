package com.koobe.common.data;

import com.koobe.common.core.KoobeApplication;
import com.koobe.common.data.domain.Draft;
import com.koobe.common.data.domain.User;
import com.koobe.common.data.domain.UserDraft;
import com.koobe.common.data.repository.DraftRepository;
import com.koobe.common.data.repository.UserDraftRepository;
import com.koobe.common.data.repository.UserRepository;
import org.junit.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by lyhcode on 2013/12/14.
 */
public class UserDraftTest {

    private static KoobeApplication application;
    private static KoobeDataService service;
    private static UserDraftRepository repository;

    private static UserRepository userRepository;
    private static DraftRepository draftRepository;

    public UserDraftTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        application = KoobeApplication.getInstance();
        service = (KoobeDataService) application.getService(KoobeDataService.class);
        repository = (UserDraftRepository) service.getRepository(UserDraftRepository.class);

        userRepository = (UserRepository) service.getRepository(UserRepository.class);
        draftRepository = (DraftRepository) service.getRepository(DraftRepository.class);
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
    public void createUpdateDelete() {
        Draft draft;
        draft = new Draft();
        draft.setName("Test a Draft Book");

        User user = userRepository.getByUserId("admin");
        assertNotNull(user);

        UserDraft relation = new UserDraft(user, draft);
        repository.save(relation);

        assertNotNull(relation);
    }
}
