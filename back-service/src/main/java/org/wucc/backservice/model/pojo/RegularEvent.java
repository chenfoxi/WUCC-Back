package org.wucc.backservice.model.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.wucc.backservice.model.EDayOfWeek;
import org.wucc.backservice.model.pojo.dict.Photo;
import org.wucc.backservice.model.pojo.relationship.RegularEventPhoto;
import org.wucc.backservice.model.pojo.relationship.RegularEventRegister;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by foxi.chen on 17/08/20.
 *
 * @author foxi.chen
 */

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RegularEvent extends AbstractEntity {

    private Date startDate, endDate;

    private Timestamp startTime, endTime, updateTime, createTime;

    @Enumerated(EnumType.ORDINAL)
    private EDayOfWeek eDayofWeek;

    private int status;

    @Lob
    @Column(columnDefinition = "text")
    private String content;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
        mappedBy = "regularEvent", orphanRemoval = true)
    private Set<RegularEventPhoto> photos = new HashSet<>();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "event_meta_id")
    private EventMetaData metaDataForR;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
        mappedBy = "regularEvent", orphanRemoval = true)
    private Set<RegularEventRegister> Users = new HashSet<>();

}
