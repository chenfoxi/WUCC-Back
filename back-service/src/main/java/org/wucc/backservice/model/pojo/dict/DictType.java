package org.wucc.backservice.model.pojo.dict;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.wucc.backservice.model.pojo.AbstractEntity;

import javax.persistence.Entity;

/**
 * Created by foxi.chen on 16/09/20.
 *
 * @author foxi.chen
 */

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DictType extends AbstractEntity {

    private String name;

    //0-for tag; 1-for like; 2-for view;
    private int type;

    public DictType(Long id) {
        super(id);
    }
}
