/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.koobe.common.data.repository;

import com.koobe.common.data.domain.Book;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author lyhcode
 */
public interface BookRepository extends CrudRepository<Book, String> {
}
