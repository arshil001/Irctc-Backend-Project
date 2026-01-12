package com.substring.Irctc.repository;

import com.substring.Irctc.entity.TrainSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainScheduleRepository extends JpaRepository<TrainSchedule,Long> {

//    @Query("SELECT ts FROM TrainSchedule ts WHERE ts.train.id=?1")
    Page<TrainSchedule> findByTrain_Id(Long trainId, Pageable pageable);
}
