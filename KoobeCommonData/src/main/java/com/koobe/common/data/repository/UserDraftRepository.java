package com.koobe.common.data.repository;

import com.koobe.common.data.domain.Draft;
import com.koobe.common.data.domain.User;
import com.koobe.common.data.domain.UserDraft;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * KGL Draft Repository
 *
 * @author lyhcode
 */
public interface UserDraftRepository extends CrudRepository<UserDraft, Long> {
    List<UserDraft> findByUser(User user);
    List<UserDraft> findByDraft(Draft draft);
}
