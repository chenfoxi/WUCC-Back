package org.wucc.backservice.repository.Impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.wucc.backservice.model.dto.CommentDTO;
import org.wucc.backservice.repository.custom.CustomCommentRepository;

import java.util.List;

/**
 * Created by foxi.chen on 23/09/20.
 *
 * @author foxi.chen
 */

@Repository
public class CustomCommentRepositoryImpl implements CustomCommentRepository {

    static final String FIND_COMMENTS_BY_POSTID_COMMENTTYPE = "SELECT " +
        "c.id, " +
        "c.content, " +
        "c.update_time, " +
        "c.post_id, " +
        "c.comment_type, " +
        "u.id as user_id, " +
        "u.avatar_url, " +
        "u.first_name, " +
        "u.last_name " +
        "FROM " +
        "`comment` c " +
        "INNER JOIN `user` u ON c.user_id = u.id " +
        "WHERE " +
        "c.comment_type = :commentType " +
        "AND c.post_id = :postId " +
        "Order by c.update_time DESC";

    static final String DELETE_COMMENT_BY_ID = "Delete from `comment` where id = :id";

    final NamedParameterJdbcTemplate jdbcTemplate;

    public CustomCommentRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(readOnly = true)
    @Override
    public List<CommentDTO> findAllCommentsByPostIdAndCommentType(Long postId, Integer commentType) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("commentType", commentType);
        mapSqlParameterSource.addValue("postId", postId);
        return jdbcTemplate.query(FIND_COMMENTS_BY_POSTID_COMMENTTYPE,
            mapSqlParameterSource,
            new BeanPropertyRowMapper<>(CommentDTO.class));
    }

    public Boolean deleteCommentById(Long id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", id);
        int result = jdbcTemplate.update(DELETE_COMMENT_BY_ID, mapSqlParameterSource);
        return result == 1;
    }
}
