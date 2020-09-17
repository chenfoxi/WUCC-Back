package org.wucc.backservice.model.pojo.relationship;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.wucc.backservice.model.pojo.Blog;
import org.wucc.backservice.model.pojo.composite.BlogTagId;
import org.wucc.backservice.model.pojo.dict.DictType;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Created by foxi.chen on 16/09/20.
 *
 * @author foxi.chen
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BlogTag {

    @EmbeddedId
    private BlogTagId blogTagId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_id", nullable = true, insertable = false, updatable = false)
    private Blog blog;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", nullable = true, insertable = false, updatable = false)
    private DictType dictType;

    private Timestamp createTime;

    private Timestamp updateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        BlogTag that = (BlogTag) o;
        return Objects.equals(blog, that.blog) &&
            Objects.equals(dictType, that.dictType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blog, dictType);
    }

}
