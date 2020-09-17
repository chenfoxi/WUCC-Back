package org.wucc.backservice.model.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by foxi.chen on 16/09/20.
 *
 * @author foxi.chen
 */

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Blog extends AbstractEntity {

    private String title;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true, insertable = false, updatable = false)
    private User user;

    @Lob
    @Column(columnDefinition = "longtext")
    private String content;

    @Lob
    @Column(columnDefinition = "text")
    private String excerpt;

    private int commentStatus;

    private int postType;

    private int auditStatus;

    private Timestamp createTime;

    private Timestamp updateTime;
}
