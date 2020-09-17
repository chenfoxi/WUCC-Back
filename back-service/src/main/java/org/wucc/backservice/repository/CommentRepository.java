package org.wucc.backservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.wucc.backservice.model.pojo.Comment;
import org.wucc.backservice.model.pojo.User;

/**
 * Created by foxi.chen on 16/09/20.
 *
 * @author foxi.chen
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

   Boolean deleteCommentByUserAndPostIdAndCommentType(User user, Long postId, Integer commentType);

}
