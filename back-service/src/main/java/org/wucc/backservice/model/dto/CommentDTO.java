package org.wucc.backservice.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by foxi.chen on 23/09/20.
 *
 * @author foxi.chen
 */

@Data
public class CommentDTO implements Serializable {

    Long id;
    String content;
    Timestamp updateTime;
    Long postId;
    Integer commentType;
    Long userId;
    String avatarUrl;
    String firstName;
    String lastName;

}
