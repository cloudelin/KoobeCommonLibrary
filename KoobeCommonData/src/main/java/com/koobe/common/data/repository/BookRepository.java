package com.koobe.common.data.repository;

import com.koobe.common.data.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * KGL Book Repository
 *
 * @author lyhcode
 */
public interface BookRepository extends CrudRepository<Book, String> {
    List<Book> findAll(Sort sort);
    Page<Book> findAll(Pageable pageable);
}
