package org.wucc.backservice.model.dto.response;

import lombok.Data;

import java.util.List;

/**
 * Created by foxi.chen on 29/07/20.
 *
 * @author foxi.chen
 */

@Data
public class JwtMsgResponse {

    private String token;
    private String type = "Bearer";
    private long id;
    private String username;
    private String email;
    private List<String> roles;

    public JwtMsgResponse(String accessToken, Long id, String username, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
