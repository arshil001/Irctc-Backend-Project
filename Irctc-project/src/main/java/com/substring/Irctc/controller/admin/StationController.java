package com.substring.Irctc.controller.admin;

import com.substring.Irctc.dto.PagedResponse;
import com.substring.Irctc.dto.StationDto;
import com.substring.Irctc.service.StationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/stations")
public class StationController {

    private StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @PostMapping
    public ResponseEntity<StationDto> createStation(
            @Valid @RequestBody StationDto stationDto){

       StationDto dto= stationService.createStation(stationDto);

       return new ResponseEntity<>(dto,HttpStatus.CREATED);

    }

    @GetMapping
    public PagedResponse<StationDto> listOfStation(
            @RequestParam(value = "page",defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc")  String sortDir
            ){
        PagedResponse<StationDto> dto =stationService.listOfStation(page, size, sortBy, sortDir);
        return dto;

    }

    @GetMapping("/{id}")
    public StationDto getById(@PathVariable Long id){

      return   stationService.getById(id);

    }
        @DeleteMapping("/{id}")
        public void delete(@PathVariable Long id){
            stationService.delete(id);

        }

    @PutMapping("/{id}")
    public ResponseEntity<StationDto> update(
                                             @RequestBody StationDto stationDto,
                                             @PathVariable Long id)
    {
       return new ResponseEntity<>(stationService.update(id,stationDto),HttpStatus.OK);
    }


}
