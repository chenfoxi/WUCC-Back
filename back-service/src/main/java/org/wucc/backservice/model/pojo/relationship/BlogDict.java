package org.wucc.backservice.model.pojo.relationship;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.wucc.backservice.model.pojo.Blog;
import org.wucc.backservice.model.pojo.composite.BlogCounterId;
import org.wucc.backservice.model.pojo.composite.BlogTagId;
import org.wucc.backservice.model.pojo.dict.DictType;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Created by foxi.chen on 7/10/20.
 *
 * @author foxi.chen
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BlogDict {
    @EmbeddedId
    private BlogCounterId blogCounterId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_id", nullable = true, insertable = false, updatable = false)
    private Blog blog;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dict_type_id", nullable = true, insertable = false, updatable = false)
    private DictType dictType;

    private int value;

    private Timestamp createTime;

    private Timestamp updateTime;

    public BlogDict(Long blogId, Long dictTypeId, int value){
        this.blog = new Blog(blogId);
        this.dictType = new DictType(dictTypeId);
        this.blogCounterId = new BlogCounterId(blogId, dictTypeId);
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        BlogDict that = (BlogDict) o;
        return Objects.equals(blog.getId(), that.blog.getId()) &&
            Objects.equals(dictType.getId(), that.dictType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(blog, dictType);
    }
}
