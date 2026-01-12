package com.substring.Irctc.repository;

import com.substring.Irctc.entity.Train;
import com.substring.Irctc.entity.TrainRoute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainRouteRepository extends JpaRepository<TrainRoute, Long> {


    List<TrainRoute> getByTrain(Train trainId);
}
