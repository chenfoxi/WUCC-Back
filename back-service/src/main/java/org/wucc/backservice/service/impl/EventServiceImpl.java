package org.wucc.backservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wucc.backservice.model.dto.SimpleDTO;
import org.wucc.backservice.repository.CustomEventRepository;
import org.wucc.backservice.service.EventService;

import java.util.List;

/**
 * Created by foxi.chen on 18/08/20.
 *
 * @author foxi.chen
 */

@Service
public class EventServiceImpl implements EventService {

    final CustomEventRepository customEventRepository;

    @Autowired
    EventServiceImpl(CustomEventRepository customEventRepository) {
        this.customEventRepository = customEventRepository;
    }

    @Override
    public List<SimpleDTO> findEventOrderBy(String orderTerm, Integer number, Integer type) {

        return customEventRepository.findRegularEventOrderBy(orderTerm, number, type);

    }
}
