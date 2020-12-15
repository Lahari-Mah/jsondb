package com.thebrgtest.jsondb.controller;

import com.thebrgtest.jsondb.domain.Input;
import com.thebrgtest.jsondb.repository.InputRepository;
import com.thebrgtest.jsondb.repository.InputSeatProjection;
import com.thebrgtest.jsondb.service.InputService;
import lombok.var;
import net.bytebuddy.TypeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SeatAvailablityController {

    private InputService inputService;
    List<InputSeatProjection> inputs;

    Pageable pageRequest = null;

    public SeatAvailablityController(InputService inputService) {
        this.inputService = inputService;
    }

    @GetMapping("/details/{productId}")
    public ResponseEntity<Input> getSeatByProduct(@PathVariable("productId")
                                                          long productId){
        Optional<Input> inputData = inputService.findById(productId);

        if (inputData.isPresent()) {
            return new ResponseEntity<>(inputData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/showSeatsAvailable")
    public ResponseEntity<List<InputSeatProjection>> getSeatByProductType(@RequestParam(required = false)
                                              Long productId, @RequestParam(required = false)
                                             Long typeId) {
        try {
            inputs = new ArrayList<InputSeatProjection>();
            inputService.findByProductIdAndTypeId(productId,typeId, null).forEach(inputs::add);

            /*Optional<InputSeatProjection> inputData =
                    inputService.findByProductIdAndTypeId(productId,typeId);

            if (inputData.isPresent()) {
                return new ResponseEntity<>(inputs, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }*/

            if (inputs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(inputs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/showSeatsAvailable/time")
    public ResponseEntity<List<InputSeatProjection>> getSeatByTime(
            @RequestParam(required = false)
                    Date startDate,

            @RequestParam(required = false)
                    Date endDate) {
        try {
            inputs = new ArrayList<InputSeatProjection>();
            inputService.findByTime(startDate,endDate,null).forEach(inputs::add);

            if (inputs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(inputs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {

        //Conversion of date on return
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor is a custom date editor
    }

    @GetMapping("/showSeatsAvailable/all")
    public ResponseEntity<List<InputSeatProjection>> getSeatsAvailable(
                    @RequestParam(required = false)
                    Long productId, @RequestParam(required = false)
                    Long typeId,@RequestParam(required = false) Date startDate,
                    @RequestParam(required = false)
                            Date endDate) {
        try {

            inputs = new ArrayList<InputSeatProjection>();
            inputService.findByAllParams(productId,typeId,startDate,endDate,null).forEach(inputs::add);

            if (inputs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(inputs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
