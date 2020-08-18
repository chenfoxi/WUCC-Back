package org.wucc.backservice.repository.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.wucc.backservice.model.dto.SimpleDTO;
import org.wucc.backservice.repository.CustomEventRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by foxi.chen on 18/08/20.
 *
 * @author foxi.chen
 */

@Repository
public class EventRepositoryImpl implements CustomEventRepository {

    static final String FIND_REGULAR_EVENT_ORDER_BY = "SELECT id, title " +
        "FROM event_meta_data " +
        "where valid_status = 0 and type = 0 " +
        "ORDER BY priority desc, :orderTerm DESC " +
        "LIMIT :number";

    static final String FIND_ONCE_EVENT_ORDER_BY = "SELECT a.id AS id , title FROM " +
        "once_event a INNER JOIN event_meta_data b ON a.event_meta_id = b.id " +
        "WHERE b.valid_status = 0 AND b.type = 1 " +
        "ORDER BY priority DESC , a. STATUS ASC , :orderTerm DESC " +
        "LIMIT :number";

    final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    EventRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(readOnly = true)
    @Override
    public List<SimpleDTO> findRegularEventOrderBy(String orderTerm, Integer number, Integer type) {

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("orderTerm", orderTerm);
        mapSqlParameterSource.addValue("number", number);
        if (type == 1) {
            return jdbcTemplate.query(FIND_ONCE_EVENT_ORDER_BY,
                mapSqlParameterSource,
                new BeanPropertyRowMapper<>(SimpleDTO.class)
            );
        }
        return jdbcTemplate.query(FIND_REGULAR_EVENT_ORDER_BY,
            mapSqlParameterSource,
            new BeanPropertyRowMapper<>(SimpleDTO.class)
        );
    }
}
