package org.wucc.backservice.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by foxi.chen on 26/08/20.
 *
 * @author foxi.chen
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {

    // 0-suc -1--error
    private Integer code;
    private T Data;
    private String errorMsg;
}
