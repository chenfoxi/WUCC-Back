package org.wucc.backservice.model.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by foxi.chen on 17/08/20.
 *
 * @author foxi.chen
 */

@MappedSuperclass
@EqualsAndHashCode
@Data
public class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Override
//    public boolean equals(Object object){
//        if (object == this) {
//            return true;
//        }
//        if (object == null) {
//            return false;
//        }
//        if (object.getClass() != this.getClass()){
//            return false;
//        }
//        AbstractEntity other = (AbstractEntity)object;
//        return other.id.equals(this.id);
//    }

}
