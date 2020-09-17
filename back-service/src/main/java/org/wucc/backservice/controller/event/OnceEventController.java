package org.wucc.backservice.controller.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wucc.backservice.model.dto.OnceEventDTO;
import org.wucc.backservice.model.dto.request.SimpleRequest;
import org.wucc.backservice.model.dto.response.CommonResponse;
import org.wucc.backservice.repository.OnceEventRegisterRepository;
import org.wucc.backservice.service.EventService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by foxi.chen on 26/08/20.
 *
 * @author foxi.chen
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/open/oevent")
public class OnceEventController {

    final EventService eventService;

    final OnceEventRegisterRepository onceEventRegisterRepository;

    @Autowired
    public OnceEventController(EventService eventService,
                               OnceEventRegisterRepository onceEventRegisterRepository) {
        this.eventService = eventService;
        this.onceEventRegisterRepository = onceEventRegisterRepository;
    }

    @PostMapping("/list")
    public ResponseEntity<?> getValidOnceEventList(@Valid @RequestBody SimpleRequest simpleRequest) {
        String orderBY = "start_time aes";
        Integer start = simpleRequest.getStart();
        Integer end = simpleRequest.getEnd();
        CommonResponse<List<OnceEventDTO>> commonResponse = new CommonResponse<>();
        try{
            commonResponse.setData(eventService.findOnceEvenOrderBy(orderBY, start, end));
            commonResponse.setCode(0);
            commonResponse.setErrorMsg("");
            return ResponseEntity.ok(commonResponse);
        }catch (Exception e){
            commonResponse.setCode(-1);
            commonResponse.setErrorMsg(e.getMessage());
            commonResponse.setData(new ArrayList<>());
            return ResponseEntity.status(500).body(commonResponse);
        }
    }

    @GetMapping("/getOEvent/{id}")
    public ResponseEntity<?> getValidOnceEventDetail(@PathVariable Long id) {
        CommonResponse<OnceEventDTO> commonResponse = new CommonResponse<>();
        try{
            commonResponse.setData(eventService.getOnceEventById(id));
            commonResponse.setCode(0);
            commonResponse.setErrorMsg("");
            return ResponseEntity.ok(commonResponse);
        }catch (Exception e){
            commonResponse.setCode(-1);
            commonResponse.setErrorMsg(e.getMessage());
            commonResponse.setData(new OnceEventDTO());
            return ResponseEntity.status(500).body(commonResponse);

        }
    }

}
