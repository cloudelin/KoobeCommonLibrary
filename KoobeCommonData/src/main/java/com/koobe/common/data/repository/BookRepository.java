package com.koobe.common.data.repository;

import com.koobe.common.data.domain.Book;
import org.springframework.data.repository.CrudRepository;

/**
 * KGL Book Repository
 *
 * @author lyhcode
 */
public interface BookRepository extends CrudRepository<Book, String> {
}
