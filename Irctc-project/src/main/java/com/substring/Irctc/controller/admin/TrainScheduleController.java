package com.substring.Irctc.controller.admin;

import com.substring.Irctc.dto.PagedResponse;
import com.substring.Irctc.dto.TrainScheduleDto;
import com.substring.Irctc.service.TrainScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/train-schedule")
public class TrainScheduleController {

    private TrainScheduleService trainScheduleService;

    public TrainScheduleController(TrainScheduleService trainScheduleService) {
        this.trainScheduleService = trainScheduleService;
    }

    @PostMapping
    public ResponseEntity<TrainScheduleDto> create(@RequestBody TrainScheduleDto trainScheduleDto){
        TrainScheduleDto dto = trainScheduleService.create(trainScheduleDto);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/trains/{trainId}")
    public PagedResponse<TrainScheduleDto> getScheduleByTrtainId(
            @PathVariable Long trainId,
            @RequestParam(value = "page",defaultValue = "0") int page,
            @RequestParam(value = "size",defaultValue = "10") int size,
            @RequestParam(value = "sortBy",defaultValue = "id") String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir ){
        return trainScheduleService.getTrainSchedulesByTrainId(trainId,page,size,sortBy,sortDir);
    }
    @PutMapping("/{id}")
    public ResponseEntity<TrainScheduleDto> update(@PathVariable Long trainScheduleId,
                                                   @RequestBody TrainScheduleDto trainScheduleDto)
    {
        TrainScheduleDto update = trainScheduleService.update(trainScheduleId, trainScheduleDto);
        return new ResponseEntity<>(update,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        trainScheduleService.delete(id);
    }
}
