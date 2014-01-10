package com.koobe.common.data;

import com.koobe.common.core.KoobeApplication;
import com.koobe.common.data.domain.Draft;
import com.koobe.common.data.domain.User;
import com.koobe.common.data.repository.DraftRepository;
import com.koobe.common.data.repository.UserRepository;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by lyhcode on 2013/12/14.
 */
public class DraftTest {

    private static KoobeApplication application;
    private static KoobeDataService service;
    private static DraftRepository repo;

    public DraftTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        application = KoobeApplication.getInstance();
        service = (KoobeDataService) application.getService(KoobeDataService.class);
        repo = (DraftRepository) service.getRepository(DraftRepository.class);
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

        assertNull(draft.getId());

        draft.setName("Test a Draft Book");
        assertNotNull(repo.save(draft));

        assertNotNull(draft.getId());

        //delete
        repo.delete(draft);


    }
}
