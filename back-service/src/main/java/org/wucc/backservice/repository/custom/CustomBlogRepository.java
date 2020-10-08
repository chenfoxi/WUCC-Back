package org.wucc.backservice.repository.custom;

import org.wucc.backservice.model.dto.BlogDTO;
import org.wucc.backservice.model.pojo.relationship.BlogDict;
import org.wucc.backservice.model.pojo.relationship.BlogTag;

import java.util.List;

/**
 * Created by foxi.chen on 29/09/20.
 *
 * @author foxi.chen
 */
public interface CustomBlogRepository {
    List<BlogDTO> getBlogListByStatusOrderByUpdateTime(Integer post_status, Integer audit_status);

    BlogDTO getBlogDetailsById(Long id);

    List<BlogDTO> getMostViewListTop10();

}
