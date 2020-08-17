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
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OnceEventUserId implements Serializable {

    @Column(name = "o_event_id")
    private Long onceEventId;

    @Column(name = "user_id")
    private Long userId;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;

        if (object == null || getClass() != object.getClass())
            return false;

        OnceEventUserId that = (OnceEventUserId) object;
        return Objects.equals(onceEventId, that.onceEventId) &&
            Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(onceEventId, userId);
    }
}
