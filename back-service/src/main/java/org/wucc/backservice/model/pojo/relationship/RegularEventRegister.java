package org.wucc.backservice.model.pojo.relationship;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.wucc.backservice.model.pojo.RegularEvent;
import org.wucc.backservice.model.pojo.User;
import org.wucc.backservice.model.pojo.composite.RegularEventUserId;

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
public class RegularEventRegister{

    @EmbeddedId
    private RegularEventUserId regularEventUserId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regular_event_id", nullable = true, insertable = false, updatable = false)
    private RegularEvent regularEvent;

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

        RegularEventRegister that = (RegularEventRegister) o;
        return Objects.equals(regularEvent, that.regularEvent) &&
            Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regularEvent, user);
    }
}
