package org.wucc.backservice.model.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Created by foxi.chen on 27/07/20.
 *
 * @author foxi.chen
 */

@Data
@Entity
public class LocalAuth {

    @Id
    private String userName;

    private String password;

    @OneToOne
    private User user;


}
