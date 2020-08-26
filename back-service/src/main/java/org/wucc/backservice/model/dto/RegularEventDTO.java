package org.wucc.backservice.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by foxi.chen on 20/08/20.
 *
 * @author foxi.chen
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class RegularEventDTO extends SimpleDTO implements Serializable {

    private Long rId;

    private Integer dayOfWeek;

    private Date startDate, endDate;

    private String description;

    private String photoUrl;

    private String street, suburb, city;

    private Timestamp startTime, endTime;

    private int status;

    private String content;

    public RegularEventDTO() {}

    public RegularEventDTO(Long id, String title, Long rId, Integer dayOfWeek, Date startDate,
                           Date endDate, String description,  String content, String street,
                           String suburb, String city, Timestamp startTime, Timestamp endTime,
                           int status, String photoUrl) {
        super(id, title);
        this.rId = rId;
        this.dayOfWeek = dayOfWeek;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.photoUrl = photoUrl;
        this.street = street;
        this.suburb = suburb;
        this.city = city;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.content = content;
    }

    public RegularEventDTO(String title, Long rId, Date startDate, Date endDate) {
        super(0L, title);
        this.rId = rId;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
