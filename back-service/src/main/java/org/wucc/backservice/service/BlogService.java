package org.wucc.backservice.service;

import org.wucc.backservice.model.dto.BlogDTO;

import java.util.List;

/**
 * Created by foxi.chen on 25/09/20.
 *
 * @author foxi.chen
 */
public interface BlogService {

    List<BlogDTO> getBlogListByStatus(Integer status);

    BlogDTO getBlogDetailById(Long id);

    Boolean saveBlog(BlogDTO blogDTO);
}
