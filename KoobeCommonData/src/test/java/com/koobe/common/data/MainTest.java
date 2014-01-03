/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koobe.common.data;

import com.koobe.common.core.KoobeApplication;
import com.koobe.common.core.service.KoobeService;
import com.koobe.common.data.dao.BookRow;
import com.koobe.common.data.domain.Book;
import com.koobe.common.data.repository.BookRepository;
import com.koobe.common.data.service.BookDataService;
import java.util.Date;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author lyhcode
 */
public class MainTest {

    private static KoobeApplication application;
    private static KoobeDataService service;

    public MainTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        application = KoobeApplication.getInstance();
        service = (KoobeDataService) application.getService(KoobeDataService.class);
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
    }

    @Test
    public void getDataService() {
        assertNotNull(service.getDataService(BookDataService.class));
    }

    @Test
    public void getDataServiceWithName() {
        assertNotNull(service.getDataService("bookDataService"));
    }

    @Test
    public void simpleQuery() {
        BookDataService ds = (BookDataService) service.getDataService(BookDataService.class);
        String name = ds.getBookName("003d34cc-74c0-4271-ae55-556198213766");
        assertNotNull(name);
        assertEquals(name, "我的第一本蘋果書：Mac OS X 10.5 Leopard");
    }

    @Test
    public void daoObjectQuery() {
        BookDataService ds = (BookDataService) service.getDataService(BookDataService.class);
        BookRow book = ds.getBook("003d34cc-74c0-4271-ae55-556198213766");
        assertNotNull(book);
        assertEquals(book.getName(), "我的第一本蘋果書：Mac OS X 10.5 Leopard");
    }

    @Test
    public void jpaQuery() {
        BookRepository repo = (BookRepository) service.getRepository(BookRepository.class);
        assertNotNull(repo);
        Book book = repo.findOne("003d34cc-74c0-4271-ae55-556198213766");
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
    public void listServices() {
        List<KoobeService> list1 = application.getServiceList();
        assertNotNull(list1);
        assert (list1.size() == 1);
        assert (list1.get(0) instanceof KoobeDataService);
    }
}
