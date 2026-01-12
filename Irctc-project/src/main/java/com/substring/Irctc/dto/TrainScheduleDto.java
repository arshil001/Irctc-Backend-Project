package com.substring.Irctc.dto;

import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainScheduleDto {

    private Long id;

    private Long trainId;

    private LocalDate runDate;

    private Integer availableSeats;
}
