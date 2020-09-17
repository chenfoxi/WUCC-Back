package org.wucc.backservice.model.pojo.composite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by foxi.chen on 17/08/20.
 *
 * @author foxi.chen
 */

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegularEventUserId implements Serializable {

    @Column(name = "regular_event_id")
    private Long regularEventId;

    @Column(name = "user_id")
    private Long userId;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;

        if (object == null || getClass() != object.getClass())
            return false;

        RegularEventUserId that = (RegularEventUserId) object;
        return Objects.equals(regularEventId, that.regularEventId) &&
            Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regularEventId, userId);
    }
}
