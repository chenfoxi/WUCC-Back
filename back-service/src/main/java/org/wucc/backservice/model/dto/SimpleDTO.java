package org.wucc.backservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by foxi.chen on 18/08/20.
 *
 * @author foxi.chen
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleDTO implements Serializable {

    private Long id;

    private String title;
}
