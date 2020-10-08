package org.wucc.backservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.wucc.backservice.model.pojo.composite.BlogCounterId;
import org.wucc.backservice.model.pojo.relationship.BlogDict;

import java.util.List;

/**
 * Created by foxi.chen on 8/10/20.
 *
 * @author foxi.chen
 */
public interface BlogDictRepository extends JpaRepository<BlogDict, BlogCounterId> {

    @Query(value = "select new org.wucc.backservice.model.pojo.relationship.BlogDict(" +
        "bt.blogCounterId.blogId, bt.blogCounterId.dictTypeId, bt.value)" +
        "from BlogDict bt " +
        "where bt.blogCounterId.blogId = :bId")
    List<BlogDict> findAllByBlog(@Param(value = "bId") Long id);


}
