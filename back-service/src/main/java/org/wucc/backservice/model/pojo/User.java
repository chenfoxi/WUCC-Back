package org.wucc.backservice.model.pojo;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by foxi.chen on 23/07/20.
 *
 * @author foxi.chen
 */

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fName;

    private String lName;

    private String email;

    private int gender;

    private String phoneNumber;

    @Lob
    @Column(columnDefinition = "text")
    private String description;

    private String avatarUrl;

    private int status;

    private Timestamp createTime;

    private Timestamp updateTime;

    @ManyToMany
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
}
