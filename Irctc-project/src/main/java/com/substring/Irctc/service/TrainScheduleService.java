package com.substring.Irctc.service;

import com.substring.Irctc.dto.PagedResponse;
import com.substring.Irctc.dto.TrainScheduleDto;

public interface TrainScheduleService {

    TrainScheduleDto create(TrainScheduleDto dto);
    PagedResponse<TrainScheduleDto> getTrainSchedulesByTrainId(Long trainId,int page,int size,String sortBy,String sortDir);

    void delete(Long trainScheduleId);
    TrainScheduleDto update(Long trainScheduleId,TrainScheduleDto trainScheduleDto);
}
