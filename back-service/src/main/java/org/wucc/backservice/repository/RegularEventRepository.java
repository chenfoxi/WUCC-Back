package org.wucc.backservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.wucc.backservice.model.dto.RegularEventDTO;
import org.wucc.backservice.model.pojo.RegularEvent;
import org.wucc.backservice.model.pojo.dict.Photo;

import java.util.List;

/**
 * Created by foxi.chen on 18/08/20.
 *
 * @author foxi.chen
 */
public interface RegularEventRepository extends CrudRepository<RegularEvent, Long> {


}
