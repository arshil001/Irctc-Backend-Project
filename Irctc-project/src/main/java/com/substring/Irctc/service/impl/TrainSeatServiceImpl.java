package com.substring.Irctc.service.impl;

import com.substring.Irctc.dto.TrainSeatDto;
import com.substring.Irctc.entity.TrainSchedule;
import com.substring.Irctc.entity.TrainSeat;
import com.substring.Irctc.exception.ResourceNotFoundException;
import com.substring.Irctc.repository.TrainScheduleRepository;
import com.substring.Irctc.repository.TrainSeatRepository;
import com.substring.Irctc.service.TrainSeatService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainSeatServiceImpl implements TrainSeatService {

    private TrainScheduleRepository trainScheduleRepository;
    private TrainSeatRepository trainSeatRepository;
    private ModelMapper modelMapper;

    public TrainSeatServiceImpl(TrainScheduleRepository trainScheduleRepository, TrainSeatRepository trainSeatRepository, ModelMapper modelMapper) {
        this.trainScheduleRepository = trainScheduleRepository;
        this.trainSeatRepository = trainSeatRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TrainSeatDto createTrainSeat(TrainSeatDto trainSeatDto) {
        TrainSchedule trainSchedule = trainScheduleRepository.findById(trainSeatDto.getTrainScheduleId()).orElseThrow(() -> new ResourceNotFoundException(" schedule not present for the given id " + trainSeatDto.getTrainScheduleId()));
        TrainSeat trainSeat = modelMapper.map(trainSeatDto, TrainSeat.class);
        trainSeat.setTrainSchedule(trainSchedule);
        TrainSeat savedTrainSeat = trainSeatRepository.save(trainSeat);
        return modelMapper.map(savedTrainSeat,TrainSeatDto.class);

    }

    @Override
    public List<TrainSeatDto> getByTrainScheduleId(Long trainScheduleId) {

        List<TrainSeat> byTrainScheduleId = trainSeatRepository.findByTrainSchedule_Id(trainScheduleId);

         return byTrainScheduleId.stream().map(trainSeat -> modelMapper.map(trainSeat, TrainSeatDto.class)).toList();
    }

    @Override
    public void deleteTrainSeat(Long trainSeatId) {
        TrainSeat trainSeat = trainSeatRepository.findById(trainSeatId).orElseThrow(() -> new ResourceNotFoundException(" train seat is not present for this id " + trainSeatId));

        trainSeatRepository.delete(trainSeat);

    }

    @Override
    public TrainSeatDto updateTrainSeat(Long trainSeatId, TrainSeatDto trainSeatDto) {
        TrainSeat trainSeat = trainSeatRepository.findById(trainSeatId).orElseThrow(() -> new ResourceNotFoundException(" train seat is not present for the given id " + trainSeatId));
        TrainSchedule trainSchedule = trainScheduleRepository.findById(trainSeatDto.getTrainScheduleId()).orElseThrow(() -> new ResourceNotFoundException(" schedule not present for the given id " + trainSeatDto.getTrainScheduleId()));
        trainSeat.setAvailableSeats(trainSeatDto.getAvailableSeats());
        trainSeat.setTotalSeats(trainSeatDto.getTotalSeats());
        trainSeat.setTrainSchedule(trainSchedule);
        trainSeat.setCoachType(trainSeatDto.getCoachType());
        trainSeat.setPrice(trainSeatDto.getPrice());
        trainSeat.setSeatNumberToAssign(trainSeatDto.getSeatNumberToAssign());
        TrainSeat save = trainSeatRepository.save(trainSeat);
        return modelMapper.map(save, TrainSeatDto.class);

    }
}
