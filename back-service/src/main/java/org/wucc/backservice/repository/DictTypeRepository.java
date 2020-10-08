package org.wucc.backservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wucc.backservice.model.pojo.dict.DictType;

import java.util.List;

/**
 * Created by foxi.chen on 8/10/20.
 *
 * @author foxi.chen
 */
public interface DictTypeRepository extends JpaRepository<DictType, Long> {
    List<DictType> findAllByType(int type);

    DictType findByType(int type);
}
