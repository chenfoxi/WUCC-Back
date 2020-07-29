package org.wucc.backservice.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by foxi.chen on 23/07/20.
 *
 * @author foxi.chen
 */

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;

    private String lastName;

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

    public User (String firstName, String lastName, String email, int gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.status = 0;
        this.updateTime = new Timestamp(System.currentTimeMillis());
        this.createTime = new Timestamp(System.currentTimeMillis());
    }
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
}
