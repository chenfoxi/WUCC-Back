package org.wucc.backservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.wucc.backservice.model.pojo.User;

import java.util.Optional;

/**
 * Created by foxi.chen on 25/07/20.
 *
 * @author foxi.chen
 */

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Boolean existsByEmail(String email);

    Boolean existsByFirstNameAndLastName(String firstName, String lastName);

    Optional<User> findByEmail(String email);
}
