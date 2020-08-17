package org.wucc.backservice.model.pojo.composite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by foxi.chen on 17/08/20.
 *
 * @author foxi.chen
 */

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnceEventPhotoId implements Serializable {

    @Column(name = "r_event_id")
    private Long onceEventId;

    @Column(name = "photo_id")
    private Long photoId;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;

        if (object == null || getClass() != object.getClass())
            return false;

        OnceEventPhotoId that = (OnceEventPhotoId) object;
        return Objects.equals(onceEventId, that.onceEventId) &&
            Objects.equals(photoId, that.photoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(onceEventId, photoId);
    }
}
