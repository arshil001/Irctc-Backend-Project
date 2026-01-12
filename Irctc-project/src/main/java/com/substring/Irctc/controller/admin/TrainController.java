package com.substring.Irctc.controller.admin;

import com.substring.Irctc.dto.PagedResponse;
import com.substring.Irctc.dto.TrainDto;
import com.substring.Irctc.service.TrainService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/trains")
public class TrainController {

    private TrainService trainService;

    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }
    

    @PostMapping
    public ResponseEntity<TrainDto> createTrain(
           @Valid @RequestBody TrainDto trainDto
    )
    {
        return  new ResponseEntity<>(trainService.addTrain(trainDto), HttpStatus.CREATED);

    }


    @GetMapping
    public PagedResponse<TrainDto> getAllTrain(
            @RequestParam(value = "page", defaultValue ="0") int page,
            @RequestParam(value = "size",defaultValue = "10") int size,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir
    ){

       return  trainService.getAllTrain(page,size,sortBy,sortDir);
    }


    @GetMapping("/{id}")
    public TrainDto getOne(
            @PathVariable Long id
    )
    {
       return trainService.getOne(id);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id)
    {
        trainService.delete(id);
    }


    @PutMapping("/{id}")
    public TrainDto update(
            @PathVariable Long id,
            @RequestBody TrainDto trainDto
    ){
      return   trainService.update(id,trainDto);
    }

}
