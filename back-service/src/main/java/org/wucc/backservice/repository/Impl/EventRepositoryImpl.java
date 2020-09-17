package org.wucc.backservice.repository.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.wucc.backservice.model.dto.OnceEventDTO;
import org.wucc.backservice.model.dto.RegularEventDTO;
import org.wucc.backservice.model.dto.SimpleDTO;
import org.wucc.backservice.repository.CustomEventRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Collections;
import java.util.List;

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
        "ORDER BY priority DESC, :orderTerm";

    static final String FIND_ONCE_EVENT_ORDER_BY = "SELECT a.id AS id , title FROM " +
        "once_event a INNER JOIN event_meta_data b ON a.event_meta_id = b.id " +
        "WHERE b.valid_status = 0 AND b.type = 1 " +
        "ORDER BY priority DESC, a. STATUS, :orderTerm";

    static final String FIND_ONCE_EVENT_DETAIL_ORDER_BY = "SELECT oe.id AS id , emd.title AS title , " +
        "emd.description AS description , p.url , emd.street , emd.suburb , emd.city , oe.start_time , " +
        "oe.end_time , oe.`status` " +
        "FROM event_meta_data emd INNER JOIN once_event oe ON emd.id = oe.event_meta_id " +
        "INNER JOIN photo p ON emd.photo_id = p.id " +
        "WHERE emd.valid_status = 0 " +
        "ORDER BY priority DESC , :orderTerm " +
        "LIMIT :start, :end";

    static final String GET_ONCE_EVENT_BY_ID = "SELECT oe.id AS id , emd.title AS title , " +
        "emd.description AS description , oe.content, p.url , emd.street , emd.suburb , " +
        "emd.city , oe.start_time , oe.end_time , oe.`status` " +
        "FROM event_meta_data emd " +
        "INNER JOIN once_event oe ON emd.id = oe.event_meta_id " +
        "INNER JOIN photo p ON emd.photo_id = p.id " +
        "WHERE oe.id = :id";

    static final String GET_R_EVENT_DETAILS_BY_MID_STATUS = "select emd.id as id, emd.title as title, re.id as rId, " +
        "re.e_dayof_week as dayOfWeek, emd.description, re.content, emd.street, emd.suburb, emd.city, re.start_time, re.end_time, " +
        "re.start_date, re.end_date, re.status, p.url as photoUrl " +
        "from regular_event re inner join event_meta_data emd " +
        "on re.event_meta_id = emd.id " +
        "inner join photo p " +
        "on emd.photo_id = p.id " +
        "where emd.id = :id and re.status = :status " +
        "order by :orderTerm " +
        "limit :start, :end";

    static final String GET_PAGED_REVENTS_BY_MID_AND_STATUS = "select emd.title as title, re.id as rId, " +
        "re.start_date, re.end_date " +
        "from regular_event re inner join event_meta_data emd " +
        "on re.event_meta_id = emd.id " +
        "where emd.id = :id and re.status= :status " +
        "order by :orderTerm " +
        "limit :start, :end";

    static final String GET_R_EVENT_BY_ID = "select emd.id as id, emd.title as title, re.id as rId, " +
        "re.e_dayof_week as dayOfWeek, emd.description, re.content, emd.street, emd.suburb, emd.city, re.start_time, re.end_time, " +
        "re.start_date, re.end_date, re.status, p.url as photoUrl " +
        "from regular_event re inner join event_meta_data emd " +
        "on re.event_meta_id = emd.id " +
        "inner join photo p " +
        "on emd.photo_id = p.id " +
        "where re.id = :id ";

    static final String GET_COUNT_R_EVENT_BY_MID_STATUS = "select count(*) as number from " +
        "regular_event re inner join event_meta_data emd " +
        "on re.event_meta_id = emd.id " +
        "where emd.id = :id and re.status = :status";

    final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    EventRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(readOnly = true)
    @Override
    public List<SimpleDTO> findEventOrderBy(String orderTerm, Integer type) {

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("orderTerm", orderTerm);
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

    @Override
    public List<OnceEventDTO> findOnceEventOrderBy(String orderTerm, Integer start, Integer end) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("orderTerm", orderTerm);
        mapSqlParameterSource.addValue("start", start);
        mapSqlParameterSource.addValue("end", end);

        return jdbcTemplate.query(FIND_ONCE_EVENT_DETAIL_ORDER_BY,
            mapSqlParameterSource,
            new BeanPropertyRowMapper<>(OnceEventDTO.class)
        );
    }

    @Override
    public OnceEventDTO getOnceEventDetailById(Long id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", id);
        return jdbcTemplate.queryForObject(GET_ONCE_EVENT_BY_ID,
            mapSqlParameterSource,
            new BeanPropertyRowMapper<>(OnceEventDTO.class)
        );
    }

    @Override
    public List<RegularEventDTO> getRegularEventsBymId(Long id, Integer start, Integer end,
                                                       Integer status, String orderTerm) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", id);
        mapSqlParameterSource.addValue("status", status);
        mapSqlParameterSource.addValue("start", start);
        mapSqlParameterSource.addValue("end", end);
        mapSqlParameterSource.addValue("orderTerm", orderTerm);
        return jdbcTemplate.query(GET_R_EVENT_DETAILS_BY_MID_STATUS,
            mapSqlParameterSource,
            new BeanPropertyRowMapper<>(RegularEventDTO.class)
        );
    }

    @Override
    public List<RegularEventDTO> getPagedREventListBymIdAndStatus(Long id, Integer status,
                                                                  Integer start, Integer end, String orderTerm) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", id);
        mapSqlParameterSource.addValue("start", start);
        mapSqlParameterSource.addValue("end", end);
        mapSqlParameterSource.addValue("status", status);
        mapSqlParameterSource.addValue("orderTerm", orderTerm);
        List<RegularEventDTO> a = jdbcTemplate.query(GET_PAGED_REVENTS_BY_MID_AND_STATUS,
            mapSqlParameterSource,
            new BeanPropertyRowMapper<>(RegularEventDTO.class)
        );
        a.sort((d1, d2) -> d2.getEndDate().compareTo(d1.getEndDate()));
        return a;
    }

    @Override
    public Integer getCountOfREventBymId(Long mId, Integer status) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", mId);
        mapSqlParameterSource.addValue("status", status);
        return jdbcTemplate.queryForObject(GET_COUNT_R_EVENT_BY_MID_STATUS,
            mapSqlParameterSource,
            (rs, rowNum) ->
                rs.getInt("number")
        );
    }

    @Override
    public RegularEventDTO getRegularEventsById(Long id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", id);
        return jdbcTemplate.queryForObject(GET_R_EVENT_BY_ID,
            mapSqlParameterSource,
            new BeanPropertyRowMapper<>(RegularEventDTO.class)
        );
    }
}
