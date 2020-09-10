package org.wucc.backservice.model.pojo.relationship;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.wucc.backservice.model.pojo.OnceEvent;
import org.wucc.backservice.model.pojo.User;
import org.wucc.backservice.model.pojo.composite.OnceEventUserId;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Created by foxi.chen on 17/08/20.
 *
 * @author foxi.chen
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnceEventRegister{

    @EmbeddedId
    private OnceEventUserId onceEventUserId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "once_event_id", nullable = true, insertable = false, updatable = false)
    private OnceEvent onceEvent;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true, insertable = false, updatable = false)
    private User user;

    private Timestamp createTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        OnceEventRegister that = (OnceEventRegister) o;
        return Objects.equals(onceEvent, that.onceEvent) &&
            Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(onceEvent, user);
    }

}
