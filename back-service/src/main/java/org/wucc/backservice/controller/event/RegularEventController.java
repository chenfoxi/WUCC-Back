package org.wucc.backservice.controller.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wucc.backservice.model.dto.RegularPageDTO;
import org.wucc.backservice.model.dto.request.SimpleRequest;
import org.wucc.backservice.model.dto.response.CommonResponse;
import org.wucc.backservice.service.EventService;

import javax.validation.Valid;

/**
 * Created by foxi.chen on 27/08/20.
 *
 * @author foxi.chen
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/open/revent")
public class RegularEventController {

    final EventService eventService;

    @Autowired
    public RegularEventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/getREventsBymId")
    public ResponseEntity<?> getREventsForPage(@Valid @RequestBody SimpleRequest simpleRequest) {
        Long id = simpleRequest.getId();
        CommonResponse<RegularPageDTO> response = new CommonResponse<>();
        try{
            response.setData(eventService.getRegularForPage(id));
            response.setCode(0);
            response.setErrorMsg("");
            return ResponseEntity.ok(response);
        } catch (Exception e){
            response.setCode(-1);
            response.setErrorMsg(e.getMessage());
            response.setData(new RegularPageDTO());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/getREvent/{id}")
    public ResponseEntity<?> getValidRegularEventDetail(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getRegularEventById(id));
    }

    @PostMapping("/rEventsList")
    public ResponseEntity<?> getPagedREventsByIdAndStatus(@Valid @RequestBody SimpleRequest simpleRequest) {
        Long id = simpleRequest.getId();
        Integer status = simpleRequest.getStatus();
        Integer start = simpleRequest.getStart();
        Integer end = simpleRequest.getEnd();
        String orderTerm = simpleRequest.getOrderTerm();
        return ResponseEntity.ok(eventService.getPagedrEventsByIdAndStatus(id, status, start, end, orderTerm));
    }

    @PostMapping("/countREvent")
    public ResponseEntity<?>getCountOfValidRegularEvent(@Valid @RequestBody SimpleRequest simpleRequest) {
        Long id = simpleRequest.getId();
        Integer status = simpleRequest.getStatus();
        CommonResponse<Integer> response = new CommonResponse<>();
        try{
            response.setData(eventService.getCountOfRegularEvent(id, status));
            response.setCode(0);
            response.setErrorMsg("");
            return ResponseEntity.ok(response);
        } catch (Exception e){
            response.setCode(-1);
            response.setErrorMsg(e.getMessage());
            response.setData(0);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
