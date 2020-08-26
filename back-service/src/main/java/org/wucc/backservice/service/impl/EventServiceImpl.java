package org.wucc.backservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wucc.backservice.model.dto.OnceEventDTO;
import org.wucc.backservice.model.dto.RegularEventDTO;
import org.wucc.backservice.model.dto.RegularPageDTO;
import org.wucc.backservice.model.dto.SimpleDTO;
import org.wucc.backservice.model.pojo.dict.Photo;
import org.wucc.backservice.repository.CustomEventRepository;
import org.wucc.backservice.repository.PhotoRepository;
import org.wucc.backservice.repository.RegularEventRepository;
import org.wucc.backservice.service.EventService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by foxi.chen on 18/08/20.
 *
 * @author foxi.chen
 */

@Service
public class EventServiceImpl implements EventService {

    final CustomEventRepository customEventRepository;

    final PhotoRepository photoRepository;

    final RegularEventRepository eventRepository;

    @Autowired
    EventServiceImpl(CustomEventRepository customEventRepository, PhotoRepository photoRepository,
                     RegularEventRepository eventRepository) {
        this.customEventRepository = customEventRepository;
        this.photoRepository = photoRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public List<SimpleDTO> findEventOrderBy(String orderTerm, Integer number, Integer type) {

        return customEventRepository.findEventOrderBy(orderTerm, number, type);

    }

    @Override
    public List<OnceEventDTO> findOnceEvenOrderBy(String orderTerm, Integer number) {
        return customEventRepository.findOnceEventOrderBy(orderTerm, number);
    }

    @Override
    public OnceEventDTO getOnceEventById(Long id) {
        return customEventRepository.getOnceEventDetailById(id);
    }

    @Override
    public List<Photo> findPhotosByoIdORrId(Long id, Integer type) {
        if (type == 0) {
            return photoRepository.findPhotosByrId(id);
        }
        if (type == 1) {
            return photoRepository.findPhotosByoId(id);
        }
        return new ArrayList<>();
    }

    @Override
    public List<RegularEventDTO> findPagedRegularEventDTOBymId(Long id, Integer start, Integer end) {
        return customEventRepository.getRegularEventsBymId(id, start, end, 0);
    }

    @Override
    public RegularEventDTO getRegularEventById(Long id) {
        return customEventRepository.getRegularEventsById(id);
    }

    @Override
    public List<RegularEventDTO> getPagedrEventsByIdAndStatus(Long id, Integer status, Integer start, Integer end) {
        return customEventRepository.getPagedREventListBymIdAndStatus(id, status, start, end);
    }

    @Override
    public RegularPageDTO getRegularForPage(Long id) {
        RegularEventDTO general = customEventRepository.getRegularEventsBymId(id, 0, 1, 0).get(0);
        RegularEventDTO last = customEventRepository.getRegularEventsBymId(id, 0, 1, 2).get(0);
        RegularPageDTO pageDTO = new RegularPageDTO();
        pageDTO.setGeneral(general);
        pageDTO.setLastFinishedEvent(last);
        pageDTO.setIncomingEvent(general);
        return pageDTO;
    }
}
