package org.wucc.backservice.model.pojo;

import lombok.Data;
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
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    public Role(ERole name){
        this.name = name;
    }
}
