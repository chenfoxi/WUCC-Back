package org.wucc.backservice.service;

import org.wucc.backservice.model.dto.SimpleDTO;

import java.util.List;

/**
 * Created by foxi.chen on 18/08/20.
 *
 * @author foxi.chen
 */
public interface EventService {

    List<SimpleDTO> findEventOrderBy(String orderTerm, Integer number, Integer type);
}
