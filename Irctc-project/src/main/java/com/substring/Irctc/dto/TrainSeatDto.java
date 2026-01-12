package com.substring.Irctc.dto;


import com.substring.Irctc.entity.CoachType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainSeatDto {

    private Long id;
    private Long trainScheduleId;
    private CoachType coachType;
    private Integer totalSeats;
    private Integer availableSeats;
    private Double price;
    private Integer seatNumberToAssign;

}
