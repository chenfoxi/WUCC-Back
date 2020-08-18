package org.wucc.backservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.wucc.backservice.model.pojo.RegularEvent;

/**
 * Created by foxi.chen on 18/08/20.
 *
 * @author foxi.chen
 */
public interface RegularEventRepository extends CrudRepository<RegularEvent, Long> {
}
