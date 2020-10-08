package org.wucc.backservice.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by foxi.chen on 8/10/20.
 *
 * @author foxi.chen
 */

@Data
public class DictTypeDTO implements Serializable {
    Long dictId;
    int value;
}
