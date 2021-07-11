package pl.jakub.dice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakub.dice.entity.SimulationEntity;
import java.util.List;


@Repository
public interface SimulationRepository extends JpaRepository<SimulationEntity, Long> {

    //JPA REPOSITORY

    List<SimulationEntity> findSimulationEntityByAmountOfWalls(int amountOfWalls);

}
