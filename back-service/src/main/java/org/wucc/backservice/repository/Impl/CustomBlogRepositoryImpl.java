package org.wucc.backservice.repository.Impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.wucc.backservice.model.dto.BlogDTO;
import org.wucc.backservice.repository.custom.CustomBlogRepository;

import java.util.List;

/**
 * Created by foxi.chen on 29/09/20.
 *
 * @author foxi.chen
 */

@Repository
public class CustomBlogRepositoryImpl implements CustomBlogRepository {

    final NamedParameterJdbcTemplate jdbcTemplate;

    public CustomBlogRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    static final String FIND_BLOGS_BY_STATUS_ORDERBY = "SELECT " +
        "b.id , " +
        "b.title , " +
        "b.excerpt , " +
        "b.img_url , " +
        "b.update_time , " +
        "b.user_id , " +
        "u.avatar_url , " +
        "u.first_name , " +
        "u.last_name " +
        "FROM " +
        "blog b " +
        "INNER JOIN USER u ON b.user_id = u.id " +
        "WHERE " +
        "b.post_type = :postStatus " + "and b.audit_status = :auditStatus " +
        "ORDER BY " +
        "b.update_time DESC ";

    static final String FIND_BLOG_BY_ID = "SELECT " +
        "b.id , " +
        "b.title , " +
        "b.excerpt , " +
        "b.content, " +
        "b.post_type, " +
        "b.comment_status, " +
        "b.audit_status, " +
        "b.create_time," +
        "b.img_url , " +
        "b.update_time , " +
        "b.user_id , " +
        "u.avatar_url , " +
        "u.first_name , " +
        "u.last_name " +
        "FROM " +
        "blog b " +
        "INNER JOIN USER u ON b.user_id = u.id " +
        "WHERE " +
        "b.id = :id ";

    static final String GET_MOST_VIEW_TOP_TEN = "select id, title from blog " +
        "inner join blog_counter  on blog.id = blog_counter.blog_id " +
        "where blog_id = id and dict_type_id = 1 and post_type = 1 " +
        "order by blog_counter.counter desc limit 10";

    @Override
    public List<BlogDTO> getBlogListByStatusOrderByUpdateTime(Integer postStatus, Integer auditStatus) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("postStatus", postStatus);
        mapSqlParameterSource.addValue("auditStatus", auditStatus);

        return jdbcTemplate.query(FIND_BLOGS_BY_STATUS_ORDERBY,
            mapSqlParameterSource,
            new BeanPropertyRowMapper<>(BlogDTO.class));
    }

    @Override
    public BlogDTO getBlogDetailsById(Long id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", id);
        return jdbcTemplate.queryForObject(FIND_BLOG_BY_ID,
            mapSqlParameterSource,
            new BeanPropertyRowMapper<>(BlogDTO.class));
    }

    @Override
    public List<BlogDTO> getMostViewListTop10() {
        return jdbcTemplate.query(GET_MOST_VIEW_TOP_TEN,
            new BeanPropertyRowMapper<>(BlogDTO.class));
    }
}
