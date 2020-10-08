package org.wucc.backservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * Created by foxi.chen on 24/09/20.
 *
 * @author foxi.chen
 */

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BlogDTO extends SimpleDTO {
    Long userId;
    String avatarUrl;
    String firstName;
    String lastName;
    String content;
    String excerpt;
    String imageUrl;
    Integer postType;
    Integer auditStatus;
    Integer commentStatus;
    Timestamp updateTime;
    Timestamp createTime;
}
