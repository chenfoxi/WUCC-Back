package org.wucc.backservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wucc.backservice.model.dto.OnceEventDTO;
import org.wucc.backservice.model.dto.RegularEventDTO;
import org.wucc.backservice.model.dto.RegularPageDTO;
import org.wucc.backservice.model.dto.SimpleDTO;
import org.wucc.backservice.model.pojo.OnceEvent;
import org.wucc.backservice.model.pojo.User;
import org.wucc.backservice.model.pojo.composite.OnceEventUserId;
import org.wucc.backservice.model.pojo.composite.RegularEventUserId;
import org.wucc.backservice.model.pojo.dict.Photo;
import org.wucc.backservice.model.pojo.relationship.OnceEventRegister;
import org.wucc.backservice.model.pojo.relationship.RegularEventRegister;
import org.wucc.backservice.repository.*;
import org.wucc.backservice.service.EventService;

import java.sql.Timestamp;
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

    final RegularEventRegisterRepository regularEventRegisterRepository;

    final OnceEventRegisterRepository onceEventRegisterRepository;

    @Autowired
    EventServiceImpl(CustomEventRepository customEventRepository,
                     PhotoRepository photoRepository,
                     RegularEventRepository eventRepository,
                     RegularEventRegisterRepository regularEventRegisterRepository,
                     OnceEventRegisterRepository onceEventRegisterRepository) {
        this.customEventRepository = customEventRepository;
        this.photoRepository = photoRepository;
        this.eventRepository = eventRepository;
        this.regularEventRegisterRepository = regularEventRegisterRepository;
        this.onceEventRegisterRepository = onceEventRegisterRepository;
    }

    @Override
    public List<SimpleDTO> findEventOrderBy(String orderTerm, Integer type) {

        return customEventRepository.findEventOrderBy(orderTerm, type);

    }

    @Override
    public List<OnceEventDTO> findOnceEvenOrderBy(String orderTerm, Integer start, Integer end) {
        return customEventRepository.findOnceEventOrderBy(orderTerm, start, end);
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

    @Override
    public Boolean checkRegisterByCompositeId(Long eventId, Long userId, Integer type) {
        if (type == 0) {
            RegularEventUserId regularEventUserId = new RegularEventUserId(eventId, userId);
            return regularEventRegisterRepository.findByRegularEventUserId(regularEventUserId).isPresent();
        }
        if (type == 1) {
            OnceEventUserId onceEventUserId = new OnceEventUserId(eventId, userId);
            return onceEventRegisterRepository.findByOnceEventUserId(onceEventUserId).isPresent();
        }
        return true;
    }

    @Override
    public Boolean joinEventByCompositeId(Long eventId, Long userId, Integer type) {
        if (type == 0) {
            RegularEventRegister regularEventRegister = new RegularEventRegister();
            regularEventRegister.setCreateTime(new Timestamp(System.currentTimeMillis()));
            regularEventRegister.setRegularEventUserId(new RegularEventUserId(eventId, userId));
            try{
                RegularEventRegister result = regularEventRegisterRepository.save(regularEventRegister);
                if (regularEventRegisterRepository.findByRegularEventUserId(result.getRegularEventUserId()).isPresent()){
                    return true;
                }
            }catch (Exception e) {
                return false;
            }

        }
        if (type == 1) {
            OnceEventRegister onceEventRegister = new OnceEventRegister();
            onceEventRegister.setCreateTime(new Timestamp(System.currentTimeMillis()));
            onceEventRegister.setOnceEventUserId(new OnceEventUserId(eventId, userId));
            try{
                OnceEventRegister result = onceEventRegisterRepository.save(onceEventRegister);
                if (onceEventRegisterRepository.findByOnceEventUserId(result.getOnceEventUserId()).isPresent()){
                    return true;
                }
            }catch (Exception e){
                return false;
            }
        }
        return false;
    }
}
