package org.wucc.backservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.wucc.backservice.model.pojo.composite.OnceEventUserId;
import org.wucc.backservice.model.pojo.composite.RegularEventUserId;
import org.wucc.backservice.model.pojo.relationship.OnceEventRegister;
import org.wucc.backservice.model.pojo.relationship.RegularEventRegister;

import java.util.Optional;

/**
 * Created by foxi.chen on 26/08/20.
 *
 * @author foxi.chen
 */
public interface OnceEventRegisterRepository extends CrudRepository<OnceEventRegister, OnceEventUserId> {


    Optional<OnceEventRegister> findByOnceEventUserId(OnceEventUserId OnceEventUserId);
}
