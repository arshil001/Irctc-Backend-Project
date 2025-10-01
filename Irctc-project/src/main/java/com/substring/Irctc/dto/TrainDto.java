package com.substring.Irctc.dto;


import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainDto {

    private Long id;


    @NotEmpty(message = "train number is required !!")
    @Size(min = 3, max = 20, message = "Invalid length of train no.")
    @Pattern(regexp = "^\\d+$", message = "Invalid no , train no contains only numbers.")
    @Id 
    private String number;



    @Pattern(regexp = "^[A-Za-z][A-Za-z -]*[A-Za-z]$", message = "Invalid train name. letters, spaces and hyphens are allowed")
    private String name;

//
//    @Email(message = "Invalid email")
//    private  String email;


    private Integer totalDistance;

    private StationDto sourceStation;


    private StationDto destinationStation;


}
