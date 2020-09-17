package org.wucc.backservice.model.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

/**
 * Created by foxi.chen on 26/08/20.
 *
 * @author foxi.chen
 */

@Data
public class SimpleRequest {

    @Positive
    Long id;

    Integer start;

    Integer end;

    Integer status;

    Integer type;

    String orderTerm;

}
