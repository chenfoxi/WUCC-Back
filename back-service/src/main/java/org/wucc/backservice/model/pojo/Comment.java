package org.wucc.backservice.model.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@NoArgsConstructor
@Entity
public class Comment extends AbstractEntity {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true, insertable = true, updatable = false)
    private User user;

    private Long postId, replyId;

    // 0-regular 1-once 2-blog
    private Integer commentType;

    @Lob
    @Column(columnDefinition = "text")
    private String content;

    private Timestamp createTime, updateTime;

    public Comment(User user, Long postId, Long replyId, Integer commentType,
            String content, Timestamp createTime, Timestamp updateTime){
        this.user = user;
        this.postId = postId;
        this.replyId = replyId;
        this.commentType = commentType;
        this.content = content;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
