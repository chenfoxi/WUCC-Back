package org.wucc.backservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.wucc.backservice.model.pojo.LocalAuth;
import org.wucc.backservice.model.pojo.User;

import java.util.Optional;

/**
 * Created by foxi.chen on 29/07/20.
 *
 * @author foxi.chen
 */
public interface LocalAuthRepository extends CrudRepository<LocalAuth, String> {

    Optional<LocalAuth> findByUsername(String username);

    Optional<LocalAuth> findByUser(User user);

    Boolean existsByUsername(String username);
}
