package com.substring.Irctc.dto;

import com.substring.Irctc.entity.Station;
import com.substring.Irctc.entity.Train;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainRouteDto {


    private Long id;
    private Train train;
    private Station station;
    private Integer stationOrder;
    private LocalTime arrivaltime;
    private LocalTime departureTime;
    private Integer haltMinutes;
    private Integer distanceFromSource;

}
