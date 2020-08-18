package org.wucc.backservice.repository;

import org.wucc.backservice.model.dto.SimpleDTO;

import java.util.List;

/**
 * Created by foxi.chen on 18/08/20.
 *
 * @author foxi.chen
 */

public interface CustomEventRepository {

    List<SimpleDTO> findRegularEventOrderBy(String orderTerm, Integer number, Integer type);
}
