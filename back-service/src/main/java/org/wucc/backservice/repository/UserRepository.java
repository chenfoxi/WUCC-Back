package org.wucc.backservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.wucc.backservice.model.pojo.User;

/**
 * Created by foxi.chen on 25/07/20.
 *
 * @author foxi.chen
 */
public interface UserRepository extends CrudRepository<User, Long> {
}
