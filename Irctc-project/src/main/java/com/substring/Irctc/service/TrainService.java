package com.substring.Irctc.service;


import com.substring.Irctc.dto.PagedResponse;
import com.substring.Irctc.dto.StationDto;
import com.substring.Irctc.dto.TrainDto;

public interface TrainService {
    TrainDto addTrain(TrainDto trainDto);

    PagedResponse<TrainDto> getAllTrain(int page, int size, String sortBy, String sortDir);

   TrainDto getOne(Long id);
   void delete(Long id);

   TrainDto update(Long id, TrainDto trainDto);
}
