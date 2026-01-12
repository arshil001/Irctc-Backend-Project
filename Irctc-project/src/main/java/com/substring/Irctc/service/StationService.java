package com.substring.Irctc.service;

import com.substring.Irctc.dto.PagedResponse;
import com.substring.Irctc.dto.StationDto;


public interface StationService {

    StationDto createStation(StationDto stationDto);

    PagedResponse<StationDto> listOfStation(int page,int size,String sortBy,String sortDir);

    StationDto getById(Long id);

    void delete(Long id);

     StationDto update(Long id, StationDto stationDto);
}
