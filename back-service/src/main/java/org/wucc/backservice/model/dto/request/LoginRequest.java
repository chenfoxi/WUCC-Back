package org.wucc.backservice.model.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by foxi.chen on 29/07/20.
 *
 * @author foxi.chen
 */

@Data
public class LoginRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
