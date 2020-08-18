package org.wucc.backservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by foxi.chen on 18/08/20.
 *
 * @author foxi.chen
 */

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnceEventDTO extends SimpleDTO implements Serializable {

    private String description;

    private String photoUrl;

    private String street, suburb, city;

    private Timestamp startTime, endTime;

    private int status;
}
