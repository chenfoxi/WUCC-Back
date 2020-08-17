package org.wucc.backservice.model.dto.request;

import lombok.Data;

import java.util.Set;

/**
 * Created by foxi.chen on 29/07/20.
 *
 * @author foxi.chen
 */

@Data
public class SignUpRequest {

    private String username;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private int gender;

    private Set<String> role;

    private String description;
}
