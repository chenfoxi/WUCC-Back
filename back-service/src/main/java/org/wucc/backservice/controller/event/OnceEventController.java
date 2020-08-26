package org.wucc.backservice.controller.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wucc.backservice.model.dto.OnceEventDTO;
import org.wucc.backservice.model.dto.request.CheckRequest;
import org.wucc.backservice.model.dto.request.SimpleRequest;
import org.wucc.backservice.model.dto.response.CommonResponse;
import org.wucc.backservice.model.pojo.composite.OnceEventUserId;
import org.wucc.backservice.repository.OnceEventRegisterRepository;
import org.wucc.backservice.repository.PhotoRepository;
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
@RequestMapping("/api/oevent/open")
public class OnceEventController {

    final EventService eventService;

    final PhotoRepository photoRepository;

    final OnceEventRegisterRepository onceEventRegisterRepository;

    @Autowired
    public OnceEventController(EventService eventService,
                               PhotoRepository photoRepository,
                               OnceEventRegisterRepository onceEventRegisterRepository) {
        this.eventService = eventService;
        this.photoRepository = photoRepository;
        this.onceEventRegisterRepository = onceEventRegisterRepository;
    }

    @PostMapping("/list")
    public ResponseEntity<?> getValidRegularEventList(@Valid @RequestBody SimpleRequest simpleRequest) {
        String orderBY = "start_time aes";
        Integer count = simpleRequest.getCount();
        CommonResponse<List<OnceEventDTO>> commonResponse = new CommonResponse<>();
        try{
            commonResponse.setData(eventService.findOnceEvenOrderBy(orderBY, count));
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
    public ResponseEntity<?> getValidOnceEventList(@PathVariable Long id) {
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

    @PostMapping ("/getPhotos")
    public ResponseEntity<?> getPhotosInOneEvent(@Valid @RequestBody SimpleRequest simpleRequest) {
        Long id = simpleRequest.getId();
        return ResponseEntity.ok(photoRepository.findPhotosByoId(id));
    }

    @PostMapping("/checkReg")
    public ResponseEntity<?> checkRegByOIdAndUId(@Valid @RequestBody CheckRequest checkRequest) {
        Long oId = checkRequest.getEventId();
        Long uId = checkRequest.getUId();
        OnceEventUserId onceEventUserId = new OnceEventUserId(oId, uId);
        CommonResponse<Boolean> commonResponse = new CommonResponse<>();
        try {
            Boolean ifAlreadyReg = onceEventRegisterRepository.findByOnceEventUserId(onceEventUserId).isPresent();
            commonResponse.setData(ifAlreadyReg);
            commonResponse.setCode(0);
            commonResponse.setErrorMsg("");
            return ResponseEntity.ok(commonResponse);
        } catch (Exception ex) {
            commonResponse.setData(false);
            commonResponse.setCode(-1);
            commonResponse.setErrorMsg(ex.getMessage());
            return ResponseEntity.status(500).body(commonResponse);
        }
    }

}
