package org.wucc.backservice.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

/**
 * Created by foxi.chen on 24/09/20.
 *
 * @author foxi.chen
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class BlogDTO extends SimpleDTO {
    Long userId;
    String avatarUrl;
    String firstName;
    String lastName;
    String content;
    String excerpt;
    String imageUrl;
    Integer auditStatus;
    Integer commentStatus;
    Timestamp updateTime;
    Timestamp createTime;

}
