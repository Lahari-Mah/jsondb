package com.thebrgtest.jsondb.service;

import com.thebrgtest.jsondb.domain.Input;
import com.thebrgtest.jsondb.repository.InputRepository;
import com.thebrgtest.jsondb.repository.InputSeatProjection;
import lombok.var;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InputService {

    private InputRepository inputRepository;

    public InputService(InputRepository inputRepository) {
        this.inputRepository = inputRepository;
    }

    public Iterable<Input> list(Pageable pageRequest){
        return inputRepository.findAll(pageRequest);
    }

    public Optional<Input> findById(long productId){
        return inputRepository.findById(productId);
    }

    /*public Optional<InputSeatProjection> findByProductIdAndTypeId(Long productId,Long typeId){
        return inputRepository.findByProductIdAndTypeId(productId,typeId);
    }*/

    public List<InputSeatProjection> findByProductIdAndTypeId(Long productId,Long typeId,Pageable pageable){
        return inputRepository.findByProductIdAndTypeId(productId,typeId,pageable);
    }

    public List<InputSeatProjection> findByTime(Date startDate, Date endDate,Pageable pageable){
        return inputRepository.findByTime(startDate,endDate,pageable);
    }

    public List<InputSeatProjection> findByAllParams(Long productId,Long typeId,
                                                     Date startDate, Date endDate,Pageable pageable){
        return inputRepository.findByAllParams(productId,typeId,startDate,endDate,pageable);
    }

    public Input save(Input input){
        return inputRepository.save(input);
    }

    public void save(List<Input> input) {
         inputRepository.saveAll(input);
    }


}
