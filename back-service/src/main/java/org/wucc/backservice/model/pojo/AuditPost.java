package org.wucc.backservice.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.sql.Timestamp;

/**
 * Created by foxi.chen on 17/09/20.
 *
 * @author foxi.chen
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AuditPost extends AbstractEntity {

    private Long postId;

    //0- blog 1- comment
    private int type;

    //0 - waiting 1- success; -1--refuse
    private int status;

    private String auditReason;

    private Timestamp createTime, updateTime;
}
