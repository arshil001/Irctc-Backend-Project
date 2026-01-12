package com.substring.Irctc.service.impl;

import com.substring.Irctc.dto.PagedResponse;
import com.substring.Irctc.dto.TrainScheduleDto;
import com.substring.Irctc.entity.Train;
import com.substring.Irctc.entity.TrainSchedule;
import com.substring.Irctc.exception.ResourceNotFoundException;
import com.substring.Irctc.repository.TrainRepository;
import com.substring.Irctc.repository.TrainScheduleRepository;
import com.substring.Irctc.service.TrainScheduleService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;




@Service
public class TrainScheduleServiceImpl implements TrainScheduleService {

    private TrainRepository trainRepository;
    private TrainScheduleRepository trainScheduleRepository;
    private ModelMapper modelMapper;

    public TrainScheduleServiceImpl(TrainRepository trainRepository, TrainScheduleRepository trainScheduleRepository, ModelMapper modelMapper) {
        this.trainRepository = trainRepository;
        this.trainScheduleRepository = trainScheduleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TrainScheduleDto create(TrainScheduleDto dto) {

        Train train = trainRepository.findById(dto.getTrainId()).orElseThrow(() -> new ResourceNotFoundException(" train not found for the given id " + dto.getTrainId()));
        TrainSchedule trainSchedule= modelMapper.map(dto, TrainSchedule.class);
        trainSchedule.setTrain(train);
        TrainSchedule savedTrainSchedule = trainScheduleRepository.save(trainSchedule);
        return modelMapper.map(savedTrainSchedule,TrainScheduleDto.class);
    }

    @Override
    public PagedResponse<TrainScheduleDto> getTrainSchedulesByTrainId(Long trainId,int page,int size,String sortBy,String sortDir) {
        Sort sort = sortDir.trim().equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page,size,sort);
        Page<TrainSchedule> trainSchedules = trainScheduleRepository.findByTrain_Id(trainId, pageable);
         Page<TrainScheduleDto> trainScheduleDto = trainSchedules.map(trainSchedule -> modelMapper.map(trainSchedule, TrainScheduleDto.class));
        return PagedResponse.fromPage(trainScheduleDto);
    }

    @Override
    public void delete(Long trainScheduleId) {

        TrainSchedule trainSchedule = trainScheduleRepository.findById(trainScheduleId).orElseThrow(() -> new ResourceNotFoundException(" schedule is not found for the given id " + trainScheduleId));
        trainScheduleRepository.delete(trainSchedule);

    }

    @Override
    public TrainScheduleDto update(Long trainScheduleId, TrainScheduleDto trainScheduleDto) {
        TrainSchedule trainSchedule = trainScheduleRepository.findById(trainScheduleId).orElseThrow(() -> new ResourceNotFoundException(" schedule is not found for the given id " + trainScheduleId));

        Train train = trainRepository.findById(trainScheduleDto.getTrainId()).orElseThrow(() -> new ResourceNotFoundException(" train not found for the given id " + trainScheduleDto.getTrainId()));

        trainSchedule.setTrain(train);
        trainSchedule.setAvailableSeats(trainScheduleDto.getAvailableSeats());
        trainSchedule.setRunDate(trainScheduleDto.getRunDate());
        TrainSchedule updatedTrainSchedule = trainScheduleRepository.save(trainSchedule);

        return modelMapper.map(updatedTrainSchedule,TrainScheduleDto.class);


    }
}
