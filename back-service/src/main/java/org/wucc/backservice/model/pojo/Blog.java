package org.wucc.backservice.model.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by foxi.chen on 16/09/20.
 *
 * @author foxi.chen
 */

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
public class Blog extends AbstractEntity {

    private String title;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true, insertable = true, updatable = true)
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

    private String imgUrl;

    public Blog(Long id) {
        super(id);
    }

    public Blog(Long id, String title, String content, String excerpt, String imgUrl,
                User user, int commentStatus, int postType, int auditStatus,
                Timestamp createTime, Timestamp updateTime) {
        super(id);
        this.title = title;
        this.content = content;
        this.excerpt = excerpt;
        this.imgUrl = imgUrl;
        this.user = user;
        this.commentStatus = commentStatus;
        this.postType = postType;
        this.auditStatus = auditStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Blog(String title, String content, String excerpt, String imgUrl,
                User user, int commentStatus, int postType, int auditStatus,
                Timestamp createTime, Timestamp updateTime) {
        this.title = title;
        this.content = content;
        this.excerpt = excerpt;
        this.imgUrl = imgUrl;
        this.user = user;
        this.commentStatus = commentStatus;
        this.postType = postType;
        this.auditStatus = auditStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
