/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koobe.common.data.service;

import com.koobe.common.data.dao.BookRow;
import com.koobe.common.data.dao.BookRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author lyhcode
 */
@Service
public class BookDataService implements DataService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public BookRow getBook(String guid) {
        String sql = "select * from Book where guid = ?";
        Object[] params = new Object[]{guid};

        return (BookRow) jdbcTemplate.queryForObject(
                sql,
                params,
                new BookRowMapper()
        );
    }

    public String getBookName(String guid) {
        String sql = "select name from Book where guid = ?";
        Object[] params = new Object[]{guid};
        
        return jdbcTemplate.queryForObject(
                sql,
                params,
                String.class
        );
    }

}
