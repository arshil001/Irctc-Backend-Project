package com.substring.Irctc.repository;


import com.substring.Irctc.entity.TrainSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainSeatRepository extends JpaRepository<TrainSeat,Long> {

    List<TrainSeat> findByTrainSchedule_Id(Long trainScheduleId);
}
