package org.wucc.backservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.wucc.backservice.model.pojo.dict.Photo;

import java.util.List;

/**
 * Created by foxi.chen on 20/08/20.
 *
 * @author foxi.chen
 */
public interface PhotoRepository extends JpaRepository<Photo, Long> {

    @Query(value = "select new org.wucc.backservice.model.pojo.dict.Photo(p.id, p.name, p.url) " +
        "from Photo p inner join OnceEventPhoto oep on oep.photo.id = p.id " +
        "where oep.onceEvent.id = :oId")
    List<Photo> findPhotosByoId(@Param("oId") Long oId);

    @Query(value = "select new org.wucc.backservice.model.pojo.dict.Photo(p.id, p.name, p.url) " +
        "from Photo p inner join RegularEventPhoto rep on rep.photo.id = p.id " +
        "where rep.regularEvent.id = :rId")
    List<Photo> findPhotosByrId(@Param("rId") Long rId);
}
