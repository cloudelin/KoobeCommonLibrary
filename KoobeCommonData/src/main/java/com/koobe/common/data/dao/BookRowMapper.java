/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.koobe.common.data.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author lyhcode
 */
public class BookRowMapper implements RowMapper {

    @Override
    public BookRow mapRow(ResultSet rs, int rowNum) throws SQLException {
        BookRow book = new BookRow();
        book.setName(rs.getString("name"));
        return book;
    }
    
}
