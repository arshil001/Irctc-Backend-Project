package com.substring.Irctc.service.impl;

import com.substring.Irctc.dto.PagedResponse;
import com.substring.Irctc.dto.StationDto;
import com.substring.Irctc.dto.TrainDto;
import com.substring.Irctc.entity.Station;
import com.substring.Irctc.entity.Train;
import com.substring.Irctc.exception.ResourceNotFoundException;
import com.substring.Irctc.repository.StationRepository;
import com.substring.Irctc.repository.TrainRepository;
import com.substring.Irctc.service.TrainService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TrainServiceImpl implements TrainService {

    private ModelMapper modelMapper;
    private TrainRepository trainRepository;
    private StationRepository stationRepository;

    public TrainServiceImpl(ModelMapper modelMapper, TrainRepository trainRepository, StationRepository stationRepository) {
        this.modelMapper = modelMapper;
        this.trainRepository = trainRepository;
        this.stationRepository = stationRepository;
    }

    @Override
    public TrainDto addTrain(TrainDto trainDto) {

     Station sourceStation=  stationRepository.findById(trainDto.getSourceStation().getId()).orElseThrow(()->new ResourceNotFoundException("station not found for the given id " + trainDto.getSourceStation().getId()));
     Station destinationStation= stationRepository.findById(trainDto.getDestinationStation().getId()).orElseThrow(()-> new ResourceNotFoundException("station not found for the given id "+trainDto.getDestinationStation().getId()));
     Train train=modelMapper.map(trainDto,Train.class);
     train.setSourceStation(sourceStation);
     train.setDestinationStation(destinationStation);
     Train savedTrain=trainRepository.save(train);
     return modelMapper.map(savedTrain,TrainDto.class);

    }

    @Override
    public PagedResponse<TrainDto> getAllTrain(int page,int size,String sortBy,String sortDir) {

        Sort sort = sortDir.trim().equalsIgnoreCase(sortDir)? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page,size,sort);
        Page<Train> train = trainRepository.findAll(pageable);
        Page<TrainDto> trainDto = train.map(train1->modelMapper.map(train1,TrainDto.class));

        return PagedResponse.fromPage(trainDto);

    }

    @Override
    public TrainDto getOne(Long id) {

       Train train= trainRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("train not found in the database for the given id"));
      return modelMapper.map(train, TrainDto.class);
    }

    @Override
    public void delete(Long id) {
       Train train= trainRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("train for the given id is already deleted "));
       trainRepository.delete(train);
    }

    @Override
    public TrainDto update(Long id, TrainDto trainDto) {
        Train train= trainRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("train not exist in which you want to update "));
        train.setName(trainDto.getName());
        train.setNumber(trainDto.getNumber());
        train.setTotalDistance(trainDto.getTotalDistance());

        Station sourceStation = stationRepository.findById(trainDto.getSourceStation().getId()).orElseThrow(()-> new ResourceNotFoundException("train not found for the given id "+trainDto.getSourceStation().getId()));
        Station destinationStation = stationRepository.findById(trainDto.getDestinationStation().getId()).orElseThrow(()-> new ResourceNotFoundException("train not found for the given id "+trainDto.getDestinationStation().getId()));
        train.setSourceStation(sourceStation);
        train.setDestinationStation(destinationStation);
        trainRepository.save(train);
        return modelMapper.map(train, TrainDto.class);

    }

}
