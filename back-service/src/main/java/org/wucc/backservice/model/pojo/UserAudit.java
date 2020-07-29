package org.wucc.backservice.model.pojo;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by foxi.chen on 27/07/20.
 *
 * @author foxi.chen
 */

@Data
@Entity
public class UserAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private User user;

    @Lob
    @Column(columnDefinition = "text")
    private String refuseReason;

    private Timestamp createTime;

    private Timestamp updateTime;
}
