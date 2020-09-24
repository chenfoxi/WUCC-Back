package org.wucc.backservice.repository.custom;

import org.wucc.backservice.model.dto.CommentDTO;

import java.util.List;

/**
 * Created by foxi.chen on 23/09/20.
 *
 * @author foxi.chen
 */
public interface CustomCommentRepository {
    List<CommentDTO> findAllCommentsByPostIdAndCommentType(Long postId, Integer commentType);

    Boolean deleteCommentById(Long id);
}
