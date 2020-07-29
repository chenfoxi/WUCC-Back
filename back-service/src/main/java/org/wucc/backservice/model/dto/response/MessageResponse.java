package org.wucc.backservice.model.dto.response;

import lombok.Data;

/**
 * Created by foxi.chen on 29/07/20.
 *
 * @author foxi.chen
 */

@Data
public class MessageResponse {
    private String message;

    public MessageResponse(String message){
        this.message = message;
    }
}
