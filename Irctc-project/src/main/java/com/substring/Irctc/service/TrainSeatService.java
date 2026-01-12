package com.substring.Irctc.service;

import com.substring.Irctc.dto.TrainSeatDto;

import java.util.List;

public interface TrainSeatService {

    TrainSeatDto createTrainSeat(TrainSeatDto trainSeatDto);
    List<TrainSeatDto> getByTrainScheduleId(Long trainScheduleId);
    void deleteTrainSeat(Long trainSeatId);
    TrainSeatDto updateTrainSeat(Long trainSeatId,TrainSeatDto trainSeatDto);
}
