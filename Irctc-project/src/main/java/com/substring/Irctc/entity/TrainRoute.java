package com.substring.Irctc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;


@Entity
@Table(name = "train_route")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainRoute {
    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;

    private Integer stationOrder;

    private LocalTime arrivaltime;

    private LocalTime departureTime;

    private Integer haltMinutes;

    private Integer distanceFromSource;



}
