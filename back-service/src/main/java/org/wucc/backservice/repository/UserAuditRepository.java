package org.wucc.backservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.wucc.backservice.model.pojo.UserAudit;

/**
 * Created by foxi.chen on 27/07/20.
 *
 * @author foxi.chen
 */

@Repository
public interface UserAuditRepository extends CrudRepository<UserAudit, Long> {

}
