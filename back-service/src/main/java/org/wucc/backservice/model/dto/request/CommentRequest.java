package org.wucc.backservice.model.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by foxi.chen on 23/09/20.
 *
 * @author foxi.chen
 */

@Data
@NoArgsConstructor
public class CommentRequest {
    private Long postId;
    private Integer commentType;
    private Long userId;
    private String content;
}
