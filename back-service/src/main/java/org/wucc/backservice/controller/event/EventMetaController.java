package org.wucc.backservice.controller.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wucc.backservice.model.dto.request.CheckRequest;
import org.wucc.backservice.model.dto.request.SimpleRequest;
import org.wucc.backservice.model.dto.response.CommonResponse;
import org.wucc.backservice.model.pojo.dict.Photo;
import org.wucc.backservice.service.EventService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by foxi.chen on 18/08/20.
 *
 * @author foxi.chen
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/open/event")
public class EventMetaController {

    final EventService eventService;


    @Autowired
    public EventMetaController(EventService eventService){
        this.eventService = eventService;
    }

    @GetMapping("/listOrderBy/{type}")
    public ResponseEntity<?> getValidEventList(@PathVariable Integer type) {
        return ResponseEntity.ok(eventService.findEventOrderBy("id", type));
    }

    @PostMapping ("/getPhotos")
    public ResponseEntity<?> getPhotosInOneEvent(@Valid @RequestBody SimpleRequest simpleRequest) {

        Long id = simpleRequest.getId();
        Integer type = simpleRequest.getType();
        CommonResponse<List<Photo>> response = new CommonResponse<>();
        try{
            response.setData(eventService.findPhotosByoIdORrId(id, type));
            response.setCode(0);
            response.setErrorMsg("");
            return ResponseEntity.ok(response);
        } catch (Exception e){
            response.setCode(-1);
            response.setErrorMsg(e.getMessage());
            response.setData(new ArrayList<>());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/checkReg")
    public ResponseEntity<?> checkRegByEventIdAndUId(@Valid @RequestBody CheckRequest checkRequest) {
        Long eventId = checkRequest.getEventId();
        Long uId = checkRequest.getUId();
        Integer type = checkRequest.getType();
        CommonResponse<Boolean> commonResponse = new CommonResponse<>();
        if (type != 0 && type != 1){
            commonResponse.setCode(-1);
            commonResponse.setErrorMsg("type value is wrong");
            commonResponse.setData(false);
            return ResponseEntity.badRequest().body(commonResponse);
        }

        try{
            Boolean ifAlreadyReg = eventService.checkRegisterByCompositeId(eventId, uId, type);
            commonResponse.setData(ifAlreadyReg);
            commonResponse.setCode(0);
            commonResponse.setErrorMsg("");
            return ResponseEntity.ok(commonResponse);
        } catch (Exception ex){
            commonResponse.setData(false);
            commonResponse.setCode(-1);
            commonResponse.setErrorMsg(ex.getMessage());
            return ResponseEntity.status(500).body(commonResponse);
        }
    }

    @PostMapping("/joinEvent")
    public ResponseEntity<?> joinEventByEventIdAndUId(@Valid @RequestBody CheckRequest checkRequest) {
        Long eventId = checkRequest.getEventId();
        Long uId = checkRequest.getUId();
        Integer type = checkRequest.getType();
        CommonResponse<Boolean> commonResponse = new CommonResponse<>();
        if (type != 0 && type != 1){
            commonResponse.setCode(-1);
            commonResponse.setErrorMsg("type value is wrong");
            commonResponse.setData(false);
            return ResponseEntity.badRequest().body(commonResponse);
        }
        try{
            Boolean success = eventService.joinEventByCompositeId(eventId, uId, type);
            commonResponse.setData(success);
            commonResponse.setCode(0);
            commonResponse.setErrorMsg("");
            return ResponseEntity.ok(commonResponse);
        }catch (Exception e){
            commonResponse.setData(false);
            commonResponse.setCode(-1);
            commonResponse.setErrorMsg(e.getMessage());
            return ResponseEntity.status(500).body(commonResponse);
        }
    }
}
