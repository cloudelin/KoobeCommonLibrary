/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.koobe.common.data.repository;

import com.koobe.common.data.domain.Book;
import com.koobe.common.data.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 *
 * @author lyhcode
 */
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByUserId(String userId);
}
