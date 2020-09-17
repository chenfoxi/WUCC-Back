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
public class RegularEventPhotoId implements Serializable {

    @Column(name = "regular_event_id")
    private Long regularEventId;

    @Column(name = "photo_id")
    private Long photoId;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;

        if (object == null || getClass() != object.getClass())
            return false;

        RegularEventPhotoId that = (RegularEventPhotoId) object;
        return Objects.equals(regularEventId, that.regularEventId) &&
            Objects.equals(photoId, that.photoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regularEventId, photoId);
    }
}
