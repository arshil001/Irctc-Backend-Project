package com.substring.Irctc.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;

    private String name;

    @Column(unique = true)

    private String email;

    private String phoneNumber;

    private String password;

    private LocalDateTime createdAt;


}
