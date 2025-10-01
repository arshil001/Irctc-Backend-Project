package com.substring.Irctc.repository;

import com.substring.Irctc.entity.Train;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainRepository extends JpaRepository<Train,Long> {

}
