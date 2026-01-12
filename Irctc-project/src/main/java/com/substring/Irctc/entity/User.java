package com.substring.Irctc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String phoneNumber;
    private String password;
    private LocalDateTime createdAt;
//    private UserRole userRole = UserRole.ROLE_NORMAL;
    @OneToMany(mappedBy = "user")
    private List<Booking> bookings;

    @ManyToMany(fetch = FetchType.EAGER)
    List<Role> roles = new ArrayList<>();


}
