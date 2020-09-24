package org.wucc.backservice.repository.custom;

import org.wucc.backservice.model.dto.OnceEventDTO;
import org.wucc.backservice.model.dto.RegularEventDTO;
import org.wucc.backservice.model.dto.SimpleDTO;

import java.util.List;

/**
 * Created by foxi.chen on 18/08/20.
 *
 * @author foxi.chen
 */

public interface CustomEventRepository {

    List<SimpleDTO> findEventOrderBy(String orderTerm, Integer type);

    List<OnceEventDTO> findOnceEventOrderBy(String orderTerm, Integer start, Integer end);

    OnceEventDTO getOnceEventDetailById(Long id);

    List<RegularEventDTO> getRegularEventsBymId(Long id, Integer start, Integer end, Integer status, String orderTerm);

    RegularEventDTO getRegularEventsById(Long id);

    List<RegularEventDTO> getPagedREventListBymIdAndStatus(Long id, Integer status,  Integer start,
                                                           Integer end, String orderTerm);

    Integer getCountOfREventBymId(Long mId, Integer status);
}
