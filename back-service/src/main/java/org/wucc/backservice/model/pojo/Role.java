package org.wucc.backservice.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.wucc.backservice.model.ERole;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by foxi.chen on 27/07/20.
 *
 * @author foxi.chen
 */

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public Role(ERole name){
        this.name = name;
    }
}
