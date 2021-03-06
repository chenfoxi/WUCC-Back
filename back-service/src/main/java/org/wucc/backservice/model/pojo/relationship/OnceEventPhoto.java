package org.wucc.backservice.model.pojo.relationship;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.wucc.backservice.model.pojo.OnceEvent;
import org.wucc.backservice.model.pojo.composite.OnceEventPhotoId;
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
public class OnceEventPhoto {

    @EmbeddedId
    private OnceEventPhotoId onceEventPhotoId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "once_event_id", nullable = true, insertable = false, updatable = false)
    private OnceEvent onceEvent;

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

        OnceEventPhoto that = (OnceEventPhoto) o;
        return Objects.equals(onceEvent, that.onceEvent) &&
            Objects.equals(photo, that.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(onceEvent, photo);
    }
}
