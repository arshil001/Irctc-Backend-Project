package com.substring.Irctc.controller.admin;

import com.substring.Irctc.dto.TrainRouteDto;
import com.substring.Irctc.service.TrainRouteService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/train-route")
public class TrainRouteController {

    private TrainRouteService trainRouteService;

    public TrainRouteController(TrainRouteService trainRouteService) {
        this.trainRouteService = trainRouteService;
    }

    @PostMapping
    public ResponseEntity<TrainRouteDto> add(
            @RequestBody TrainRouteDto trainRouteDto){

      return  new ResponseEntity<>(trainRouteService.add(trainRouteDto), HttpStatus.CREATED);
    }
    @GetMapping("/train/{id}")
    public ResponseEntity<List<TrainRouteDto>> getAllRoutesByTrain(@PathVariable Long id){

        List<TrainRouteDto> dto=trainRouteService.getRoutesByTrain(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public TrainRouteDto update(@PathVariable Long id, TrainRouteDto dto){

        return trainRouteService.updateRoute(id,dto);

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        trainRouteService.deleteRoute(id);

    }
}
