package org.wucc.backservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.wucc.backservice.model.ERole;
import org.wucc.backservice.model.pojo.Role;

import java.util.Optional;

/**
 * Created by foxi.chen on 29/07/20.
 *
 * @author foxi.chen
 */
public interface RoleRepository extends CrudRepository<Role, Integer> {

    Optional<Role> findByName(ERole name);
}

