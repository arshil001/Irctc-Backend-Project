package com.substring.Irctc.service.impl;

import com.substring.Irctc.dto.PagedResponse;
import com.substring.Irctc.dto.StationDto;
import com.substring.Irctc.entity.Station;
import com.substring.Irctc.exception.ResourceNotFoundException;
import com.substring.Irctc.repository.StationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public  class StationServiceImpl implements com.substring.Irctc.service.StationService {

    private ModelMapper modelMapper;
    private StationRepository stationRepository;

    public StationServiceImpl(ModelMapper modelMapper, StationRepository stationRepository) {
        this.modelMapper = modelMapper;
        this.stationRepository = stationRepository;
    }

    @Override
    public ResponseEntity<StationDto> createStation(StationDto stationDto) {
       Station station= modelMapper.map(stationDto, Station.class);

      Station savedStation= stationRepository.save(station);

      return new ResponseEntity<>(modelMapper.map(savedStation,StationDto.class), HttpStatus.CREATED);
    }



    @Override
    public PagedResponse<StationDto> listOfStation(int page, int size, String sortBy, String sortDir) {

        Sort sort = sortDir.trim().equals(sortDir)? Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page,size,sort);
        Page<Station> stations = stationRepository.findAll(pageable);
        Page<StationDto> dto = stations.map(station -> modelMapper.map(station,StationDto.class));
        return PagedResponse.fromPage(dto);

    }

    @Override
    public StationDto getById(Long id) {

        Station station = stationRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("station is not foud in database for the given id"));

        return modelMapper.map(station,StationDto.class);
    }

    @Override
    public void delete(Long id) {
        Station station = stationRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("station is not foud in database for the given id"));

        stationRepository.delete(station);
    }

    @Override
    public StationDto update(Long id, StationDto stationDto) {
        Station station =stationRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(" station not found in database for the given id"));

        station.setName(stationDto.getName());
        station.setCity(stationDto.getCity());
        station.setState(stationDto.getState());
        station.setCode(stationDto.getCode());

      Station station1=  stationRepository.save(station);

      return modelMapper.map(station1, StationDto.class);



    }






}
