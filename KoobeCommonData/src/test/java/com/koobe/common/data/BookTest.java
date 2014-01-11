package com.koobe.common.data;

import com.koobe.common.core.KoobeApplication;
import com.koobe.common.core.service.KoobeService;
import com.koobe.common.data.dao.BookRow;
import com.koobe.common.data.domain.Book;
import com.koobe.common.data.repository.BookRepository;
import com.koobe.common.data.service.BookDataService;
import org.junit.*;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author lyhcode
 */
public class BookTest {

    private static KoobeApplication application;
    private static KoobeDataService service;
    private static BookRepository repository;

    public BookTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        application = KoobeApplication.getInstance();
        service = (KoobeDataService) application.getService(KoobeDataService.class);
        repository = (BookRepository)service.getRepository(BookRepository.class);
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
    public void status() {
        assertNotNull(application);
        assertNotNull(service);
        assertNotNull(repository);
    }

    @Test
    public void jpaQuery() {
        Book book = repository.findOne("003d34cc-74c0-4271-ae55-556198213766");
        assertNotNull(book);
        assert "我的第一本蘋果書：Mac OS X 10.5 Leopard".equals(book.getName());
        assert "koobecloudepub".equals(book.getBucket());
        assert "image_1".equals(book.getCoverId());
        assert "left".equals(book.getFlipOrder());
        assert 482 == book.getCoverWidth();
        assert 652 == book.getCoverHeight();
        assertNotNull(book.getModifyDate());
        assert book.getModifyDate().before(new Date());
    }

    @Test
    public void sorting() {
     }
}
