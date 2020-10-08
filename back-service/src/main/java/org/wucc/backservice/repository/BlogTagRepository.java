package org.wucc.backservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.wucc.backservice.model.pojo.composite.BlogTagId;
import org.wucc.backservice.model.pojo.relationship.BlogTag;

import java.util.List;

/**
 * Created by foxi.chen on 8/10/20.
 *
 * @author foxi.chen
 */
public interface BlogTagRepository extends JpaRepository<BlogTag, BlogTagId> {

    @Query(value = "select " +
        "new org.wucc.backservice.model.pojo.relationship.BlogTag(bt.blogTagId.blogId, bt.blogTagId.tagId) " +
        "from BlogTag bt " +
        "where bt.blogTagId.blogId = :bId")
    List<BlogTag> findAllByBlogId(@Param(value = "bId") Long id);
}
