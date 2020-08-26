package org.wucc.backservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by foxi.chen on 26/08/20.
 *
 * @author foxi.chen
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegularPageDTO {
    private RegularEventDTO General;
    private RegularEventDTO LastFinishedEvent;
    private RegularEventDTO IncomingEvent;

}
