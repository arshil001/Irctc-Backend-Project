package com.substring.Irctc.controller.admin;

import com.substring.Irctc.dto.TrainSeatDto;


import com.substring.Irctc.service.TrainSeatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin/train-seat")
public class TrainSeatController {


    private TrainSeatService trainSeatService;

    public TrainSeatController(TrainSeatService trainSeatService) {
        this.trainSeatService = trainSeatService;
    }

    @PostMapping
    public ResponseEntity<TrainSeatDto> create(@RequestBody TrainSeatDto trainSeatDto){

        TrainSeatDto trainSeat = trainSeatService.createTrainSeat(trainSeatDto);
        return new ResponseEntity<>(trainSeat, HttpStatus.CREATED);
    }

    @GetMapping("/schedule/{scheduleId}")
    public List<TrainSeatDto> getALlSeat(@PathVariable Long scheduleId){
        List<TrainSeatDto> byTrainScheduleId = trainSeatService.getByTrainScheduleId(scheduleId);
        return byTrainScheduleId;
    }

    @DeleteMapping("/{seatId}")
    public void delete(@PathVariable Long seatId){
        trainSeatService.deleteTrainSeat(seatId);
    }

    @PutMapping("/{seatId}")
    public TrainSeatDto update(@PathVariable Long seatId, @RequestBody TrainSeatDto trainSeatDto){

        return trainSeatService.updateTrainSeat(seatId,trainSeatDto);
    }

}
