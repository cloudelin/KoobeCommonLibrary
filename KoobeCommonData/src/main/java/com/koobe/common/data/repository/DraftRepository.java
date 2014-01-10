package com.koobe.common.data.repository;

import com.koobe.common.data.domain.Draft;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * KGL Draft Repository
 *
 * @author lyhcode
 */
public interface DraftRepository extends CrudRepository<Draft, Long> {
    List<Draft> findByName(String name);
}
