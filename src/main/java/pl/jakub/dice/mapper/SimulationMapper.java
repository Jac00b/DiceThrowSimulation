package pl.jakub.dice.mapper;

import org.springframework.stereotype.Service;
import pl.jakub.dice.entity.SimulationEntity;
import pl.jakub.dice.model.Simulation;

@Service
public class SimulationMapper {

    //MAPPERS

    public static SimulationEntity toEntity(Simulation simulation) {
        SimulationEntity simulationEntity = new SimulationEntity();
        simulationEntity.setId(simulation.getId());
        simulationEntity.setAmountOfDices(simulation.getAmountOfDices());
        simulationEntity.setAmountOfThrows(simulation.getAmountOfThrows());
        simulationEntity.setAmountOfWalls(simulation.getAmountOfWalls());
        simulationEntity.setSumOfResults(simulation.getSumOfResults());
        return simulationEntity;

    }

    public static Simulation toDto(SimulationEntity simulationEntity){
        Simulation simulation = new Simulation();
        simulation.setId(simulationEntity.getId());
        simulation.setAmountOfDices(simulationEntity.getAmountOfDices());
        simulation.setAmountOfThrows(simulationEntity.getAmountOfThrows());
        simulation.setAmountOfWalls(simulationEntity.getAmountOfWalls());
        simulation.setSumOfResults(simulationEntity.getSumOfResults());
        return simulation;
    }

}
