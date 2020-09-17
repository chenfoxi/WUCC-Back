package org.wucc.backservice.model.pojo.relationship;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.wucc.backservice.model.pojo.RegularEvent;
import org.wucc.backservice.model.pojo.composite.RegularEventPhotoId;
import org.wucc.backservice.model.pojo.dict.Photo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Created by foxi.chen on 17/08/20.
 *
 * @author foxi.chen
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegularEventPhoto {

    @EmbeddedId
    private RegularEventPhotoId regularEventPhotoId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regular_event_id", nullable = true, insertable = false, updatable = false)
    private RegularEvent regularEvent;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id", nullable = true, insertable = false, updatable = false)
    private Photo photo;

    private Timestamp createTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        RegularEventPhoto that = (RegularEventPhoto) o;
        return Objects.equals(regularEvent, that.regularEvent) &&
            Objects.equals(photo, that.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regularEvent, photo);
    }
}
