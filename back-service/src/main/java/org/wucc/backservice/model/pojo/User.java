package org.wucc.backservice.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.NaturalIdCache;
import org.wucc.backservice.model.pojo.relationship.OnceEventRegister;
import org.wucc.backservice.model.pojo.relationship.RegularEventRegister;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
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
@NaturalIdCache
@Cache(
    usage = CacheConcurrencyStrategy.READ_WRITE
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;

    private String lastName;

    @NaturalId
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

    public User (Long id){
        this.id = id;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @OneToMany(
        fetch = FetchType.LAZY,
        mappedBy = "user"
    )
    private Set<RegularEventRegister> regularEvents = new HashSet<>();

    @OneToMany(
        fetch = FetchType.LAZY,
        mappedBy = "user"
    )
    private Set<OnceEventRegister> onceEvents = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
