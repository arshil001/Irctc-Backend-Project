package com.substring.Irctc.service;

import com.substring.Irctc.dto.TrainRouteDto;

import java.util.List;

public interface TrainRouteService {

    TrainRouteDto add(TrainRouteDto trainRouteDto);
    List<TrainRouteDto> getRoutesByTrain(Long trainId);
    TrainRouteDto updateRoute(Long id , TrainRouteDto dto);
    void deleteRoute(Long id);
}
