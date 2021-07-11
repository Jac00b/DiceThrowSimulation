package pl.jakub.dice.service;

import org.springframework.stereotype.Service;
import pl.jakub.dice.entity.SimulationEntity;
import pl.jakub.dice.mapper.SimulationMapper;
import pl.jakub.dice.model.Simulation;
import pl.jakub.dice.repository.SimulationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SimulationService {

    private SimulationRepository simulationRepository;


    public SimulationService(SimulationRepository simulationRepository) {
        this.simulationRepository = simulationRepository;
    }

    public List<Simulation> findAll(){
        return simulationRepository.findAll()
                .stream()
                .map(SimulationMapper::toDto)
                .collect(Collectors.toList());
    }

    public Simulation save(Simulation simulation){
        return mapAndSave(simulation);

    }

    public List<Simulation> findByAmountOfWalls(int amountOfWalls){
        return simulationRepository.findSimulationEntityByAmountOfWalls(amountOfWalls).stream()
                .map(SimulationMapper::toDto)
                .collect(Collectors.toList());
    }

    private Simulation mapAndSave(Simulation simulation) {
        SimulationEntity simulationEntity = SimulationMapper.toEntity(simulation);
        SimulationEntity savedSimulation = simulationRepository.save(simulationEntity);
        return SimulationMapper.toDto(savedSimulation);
    }
}
