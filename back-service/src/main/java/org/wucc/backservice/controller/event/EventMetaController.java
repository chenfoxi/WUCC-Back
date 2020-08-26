package org.wucc.backservice.controller.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wucc.backservice.model.dto.RegularPageDTO;
import org.wucc.backservice.model.dto.request.SimpleRequest;
import org.wucc.backservice.model.dto.response.CommonResponse;
import org.wucc.backservice.repository.PhotoRepository;
import org.wucc.backservice.service.EventService;

import javax.validation.Valid;


/**
 * Created by foxi.chen on 18/08/20.
 *
 * @author foxi.chen
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/event/open")
public class EventMetaController {

    final EventService eventService;

    final PhotoRepository photoRepository;

    @Autowired
    public EventMetaController (EventService eventService, PhotoRepository photoRepository) {
        this.eventService = eventService;
        this.photoRepository = photoRepository;
    }

    @GetMapping("/listOrderBy/{type}")
    public ResponseEntity<?> getValidRegularEventList(@PathVariable Integer type) {
        return ResponseEntity.ok(eventService.findEventOrderBy("id", 5, type));
    }

    @PostMapping ("/getPhotos")
    public ResponseEntity<?> getPhotosInOneEvent(@Valid @RequestBody SimpleRequest simpleRequest) {
        Long id = simpleRequest.getId();
        return ResponseEntity.ok(photoRepository.findPhotosByrId(id));
    }

    @PostMapping ("/getREventsBymId")
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
    public ResponseEntity<?> getValidRegularEventList(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getRegularEventById(id));
    }

    @PostMapping("/rEventsList")
    public ResponseEntity<?> getPagedREventsByIdAndStatus(@Valid @RequestBody SimpleRequest simpleRequest) {
        Long id = simpleRequest.getId();
        Integer status = simpleRequest.getStatus();
        Integer start = simpleRequest.getStart();
        Integer end = simpleRequest.getEnd();
        return ResponseEntity.ok(eventService.getPagedrEventsByIdAndStatus(id, status, start, end));
    }
}
