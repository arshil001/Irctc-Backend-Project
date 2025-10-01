package com.substring.Irctc.service.impl;

import com.substring.Irctc.dto.TrainRouteDto;
import com.substring.Irctc.entity.Station;
import com.substring.Irctc.entity.Train;
import com.substring.Irctc.entity.TrainRoute;
import com.substring.Irctc.exception.ResourceNotFoundException;
import com.substring.Irctc.repository.StationRepository;
import com.substring.Irctc.repository.TrainRepository;
import com.substring.Irctc.repository.TrainRouteRepository;
import com.substring.Irctc.service.TrainRouteService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TrainRouteServiceImpl implements TrainRouteService {
    private TrainRepository trainRepository;
    private StationRepository stationRepository;
    private TrainRouteRepository trainRouteRepository;
    private ModelMapper modelMapper;

    public TrainRouteServiceImpl(TrainRepository trainRepository, StationRepository stationRepository, TrainRouteRepository trainRouteRepository, ModelMapper modelMapper) {
        this.trainRepository = trainRepository;
        this.stationRepository = stationRepository;
        this.trainRouteRepository = trainRouteRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TrainRouteDto add(TrainRouteDto trainRouteDto) {

        Train train=this.trainRepository.findById(trainRouteDto.getTrain().getId()).orElseThrow(()->new ResourceNotFoundException("train not found with the given id "+trainRouteDto.getTrain().getId()));
       Station station= this.stationRepository.findById(trainRouteDto.getStation().getId()).orElseThrow(()->new ResourceNotFoundException("station not found with the given id"));

        TrainRoute trainRoute=modelMapper.map(trainRouteDto, TrainRoute.class);
        trainRoute.setTrain(train);
        trainRoute.setStation(station);
       TrainRoute savedTrainRoute = trainRouteRepository.save(trainRoute);

       return modelMapper.map(savedTrainRoute,TrainRouteDto.class);




    }

    @Override
    public List<TrainRouteDto> getRoutesByTrain(Long trainId) {
       Train train= trainRepository.findById(trainId).orElseThrow(()->new ResourceNotFoundException(" train not found with the given id"+trainId));
       List<TrainRoute> trainRoutes=trainRouteRepository.getByTrain(train);
       List<TrainRouteDto>dto=trainRoutes.stream().map(trainRoute -> modelMapper.map(trainRoute,TrainRouteDto.class)).toList();
       return dto;

    }

    @Override
    public TrainRouteDto updateRoute(Long id, TrainRouteDto dto) {
        TrainRoute existingTrainRoute= trainRouteRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Train route not found for the given id"+id));
       Train train= trainRepository.findById(dto.getTrain().getId()).orElseThrow(()->new ResourceNotFoundException("Train not found with the given id"+dto.getTrain().getId()));
        Station station= stationRepository.findById(dto.getStation().getId()).orElseThrow(()->new ResourceNotFoundException("Station not found with the given id"+dto.getStation().getId()));

       existingTrainRoute.setTrain(train);
       existingTrainRoute.setStation(station);
       existingTrainRoute.setStationOrder(dto.getStationOrder());
       existingTrainRoute.setArrivaltime(dto.getArrivaltime());
       existingTrainRoute.setDepartureTime(dto.getDepartureTime());
       existingTrainRoute.setDistanceFromSource(dto.getDistanceFromSource());
       existingTrainRoute.setDistanceFromSource(dto.getDistanceFromSource());

       TrainRoute trainRoute=trainRouteRepository.save(existingTrainRoute);

       return modelMapper.map(trainRoute,TrainRouteDto.class);



    }

    @Override
    public void deleteRoute(Long id) {

        TrainRoute existingTrainRoute= trainRouteRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Train route not found for the given id"+id));

        trainRouteRepository.delete(existingTrainRoute);


    }
}
