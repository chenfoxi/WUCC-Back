package org.wucc.backservice.model.pojo.dict;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.wucc.backservice.model.pojo.AbstractEntity;
import org.wucc.backservice.model.pojo.relationship.OnceEventPhoto;
import org.wucc.backservice.model.pojo.relationship.RegularEventPhoto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
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
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Photo extends AbstractEntity {
    private String name;
    private String url;
    private Timestamp createTime, updateTime;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
        mappedBy = "photo", orphanRemoval = true)
    private Set<RegularEventPhoto> regularEvents = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
        mappedBy = "photo", orphanRemoval = true)
    private Set<OnceEventPhoto> onceEvents = new HashSet<>();
}
