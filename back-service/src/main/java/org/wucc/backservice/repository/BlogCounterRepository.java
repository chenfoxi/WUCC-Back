package org.wucc.backservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.wucc.backservice.model.pojo.composite.BlogCounterId;
import org.wucc.backservice.model.pojo.relationship.BlogCounter;

/**
 * Created by foxi.chen on 8/10/20.
 *
 * @author foxi.chen
 */
public interface BlogCounterRepository extends JpaRepository<BlogCounter, BlogCounterId> {

    @Query(value = "select bc.counter " +
        "from BlogCounter bc " +
        "where bc.blogCounterId.blogId = :bId and bc.blogCounterId.dictTypeId = :dictId")
    int findCounterByBlogIdAndDictId(@Param(value = "bId") Long blogId, @Param(value = "dictId") Long dictId);
}
