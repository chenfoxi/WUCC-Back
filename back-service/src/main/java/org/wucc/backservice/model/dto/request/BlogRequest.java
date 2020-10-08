package org.wucc.backservice.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.wucc.backservice.model.dto.DictTypeDTO;
import org.wucc.backservice.model.pojo.dict.DictType;

import java.util.List;

/**
 * Created by foxi.chen on 29/09/20.
 *
 * @author foxi.chen
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogRequest {
    private Long id;
    private Long userId;
    private String content, title, excerpt, imgUrl;
    private Integer auditStatus, postType, commentStatus;
    private List<Long> tags;
    private List<DictTypeDTO> settings;
}
