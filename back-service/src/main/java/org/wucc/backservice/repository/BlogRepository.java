package org.wucc.backservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.wucc.backservice.model.pojo.Blog;

import java.sql.Timestamp;


/**
 * Created by foxi.chen on 24/09/20.
 *
 * @author foxi.chen
 */
public interface BlogRepository extends JpaRepository<Blog, Long> {

    @Transactional
    @Modifying
    @Query("update Blog b set b.auditStatus= :auditStatus, b.commentStatus = :commentStatus, b.content = :content, " +
        "b.excerpt = :excerpt, b.imgUrl = :imgUrl, b.postType = :postType, b.title = :title, " +
        "b.updateTime = :updateTime where b.id = :id")
    void updateBlog(@Param(value = "id") Long id, @Param(value = "title") String title,
                    @Param(value = "content") String content, @Param(value = "excerpt") String excerpt,
                    @Param(value = "imgUrl") String imgUrl, @Param(value = "updateTime")Timestamp updateTime,
                    @Param(value = "auditStatus") Integer auditStatus, @Param(value = "commentStatus") Integer commentStatus,
                    @Param(value = "postType") Integer postType);
}
