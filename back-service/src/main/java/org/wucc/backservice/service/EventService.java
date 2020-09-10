package org.wucc.backservice.service;

import org.wucc.backservice.model.dto.OnceEventDTO;
import org.wucc.backservice.model.dto.RegularEventDTO;
import org.wucc.backservice.model.dto.RegularPageDTO;
import org.wucc.backservice.model.dto.SimpleDTO;
import org.wucc.backservice.model.pojo.dict.Photo;

import java.util.List;

/**
 * Created by foxi.chen on 18/08/20.
 *
 * @author foxi.chen
 */
public interface EventService {

    List<SimpleDTO> findEventOrderBy(String orderTerm, Integer type);

    List<OnceEventDTO> findOnceEvenOrderBy(String orderTerm, Integer start, Integer end);

    OnceEventDTO getOnceEventById(Long id);

    List<Photo> findPhotosByoIdORrId(Long id, Integer type);

    List<RegularEventDTO> findPagedRegularEventDTOBymId(Long id, Integer start, Integer end);

    RegularEventDTO getRegularEventById(Long id);

    List<RegularEventDTO> getPagedrEventsByIdAndStatus(Long id, Integer status, Integer start, Integer end);

    RegularPageDTO getRegularForPage(Long id);

    Boolean checkRegisterByCompositeId(Long eventId, Long userId, Integer type);

    Boolean joinEventByCompositeId(Long eventId, Long userId, Integer type);
}
