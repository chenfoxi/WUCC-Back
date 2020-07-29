package org.wucc.backservice.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
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
@AllArgsConstructor
@NoArgsConstructor
public class LocalAuth {

    @Id
    @Column(name="user_name")
    private String username;

    private String password;

    @OneToOne
    private User user;


}
