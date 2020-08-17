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

    private int type;

    private Timestamp updateTime, createTime;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
        mappedBy = "metaDataForR", orphanRemoval = true)
    private Set<RegularEvent> regularEventSet = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
        mappedBy = "metaDataForO", orphanRemoval = true)
    private Set<OnceEvent> EventSet = new HashSet<>();


}
