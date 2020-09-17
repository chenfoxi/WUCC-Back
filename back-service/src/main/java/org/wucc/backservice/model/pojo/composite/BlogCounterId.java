package org.wucc.backservice.model.pojo.composite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by foxi.chen on 16/09/20.
 *
 * @author foxi.chen
 */

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogCounterId implements Serializable {
    @Column(name = "blog_id")
    private Long blogId;

    @Column(name = "dict_type_id")
    private Long dictTypeId;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;

        if (object == null || getClass() != object.getClass())
            return false;

        BlogCounterId that = (BlogCounterId) object;
        return Objects.equals(blogId, that.blogId) &&
            Objects.equals(dictTypeId, that.dictTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blogId, dictTypeId);
    }
}
