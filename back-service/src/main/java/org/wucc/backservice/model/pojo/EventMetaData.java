package org.wucc.backservice.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.wucc.backservice.model.pojo.dict.Photo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by foxi.chen on 17/08/20.
 *
 * @author foxi.chen
 */

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventMetaData extends AbstractEntity {

    private String title, street, suburb, city;

    @Lob
    @Column(columnDefinition = "text")
    private String description;

    @OneToOne
    private Photo photo;

    // validStatus 0=valid -1=invalid
    // priority 1-5 low to high
    private int type, validStatus, priority;

    private Timestamp updateTime, createTime;

    @OneToMany(fetch = FetchType.LAZY,
        mappedBy = "metaDataForR" )
    private Set<RegularEvent> regularEventSet = new HashSet<>();

}
