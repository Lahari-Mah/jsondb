package com.thebrgtest.jsondb.controller;

import com.thebrgtest.jsondb.JsondbApplication;
import com.thebrgtest.jsondb.domain.Input;
import com.thebrgtest.jsondb.service.InputService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/input")
public class InputController {

    private InputService inputService;
    private static final Logger log = LoggerFactory.getLogger(InputController.class);

    public InputController(InputService inputService) {
        this.inputService = inputService;
    }
    Pageable pageRequest;

    @GetMapping("/list")
    public Iterable<Input> list(){
        try {
             pageRequest = createPageRequest();
        }catch (Exception e){
            log.error("Exception at pageable.."+e.getMessage());
        }
        return inputService.list(pageRequest);
    }

    private Pageable createPageRequest() {
        return
               PageRequest.of(1,
                10,
                Sort.by(Sort.Direction.DESC, "eventId")
        );
    }
}
