package org.wucc.backservice.model.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.wucc.backservice.model.pojo.composite.BlogUserId;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by foxi.chen on 16/09/20.
 *
 * @author foxi.chen
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteBlog {
    @EmbeddedId
    private BlogUserId blogTagId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_id", nullable = true, insertable = false, updatable = false)
    private Blog blog;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true, insertable = false, updatable = false)
    private User user;

    // 0- like, 1-dislike
    private int status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        FavoriteBlog that = (FavoriteBlog) o;
        return Objects.equals(blog, that.blog) &&
            Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blog, user);
    }
}
