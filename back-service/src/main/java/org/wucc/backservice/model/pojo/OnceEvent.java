package org.wucc.backservice.model.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.wucc.backservice.model.pojo.dict.Photo;
import org.wucc.backservice.model.pojo.relationship.OnceEventPhoto;
import org.wucc.backservice.model.pojo.relationship.OnceEventRegister;
import org.wucc.backservice.model.pojo.relationship.RegularEventRegister;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OnceEvent extends AbstractEntity {

    private Timestamp startTime, endTime, updateTime, createTime;

    private int status;

    @Lob
    @Column(columnDefinition = "text")
    private String content;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
        mappedBy = "onceEvent", orphanRemoval = true)
    private Set<OnceEventPhoto> photos = new HashSet<>();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "event_meta_id")
    private EventMetaData metaDataForO;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
        mappedBy = "onceEvent", orphanRemoval = true)
    private Set<OnceEventRegister> Users = new HashSet<>();

}
