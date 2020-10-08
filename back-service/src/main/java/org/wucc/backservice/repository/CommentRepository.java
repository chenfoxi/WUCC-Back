package org.wucc.backservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wucc.backservice.model.pojo.Comment;

/**
 * Created by foxi.chen on 16/09/20.
 *
 * @author foxi.chen
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
