package pl.jakub.dice.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.jakub.dice.model.Simulation;
import pl.jakub.dice.service.SimulationService;

import java.net.URI;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("api/simulation")
public class SimulationRestController {

    private SimulationService simulationService;

    public SimulationRestController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @GetMapping("")
    public List<Simulation> findAll() {
            return simulationService.findAll();
    }


    @PostMapping
    public ResponseEntity<Simulation> save(@RequestBody Simulation simulation) {
        if (simulation.getId() != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Zapisywana symulacja nie może miec ustawionego id");

        int sum = sumResult(simulation);
        simulation.setSumOfResults(sum);
        Simulation savedSimulation = simulationService.save(simulation);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(savedSimulation.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedSimulation);
    }

    private int sumResult(Simulation simulation) {
        Random random = new Random();
        int sum = 0;
        int i = 0;
        while (i <= simulation.getAmountOfThrows()){
            for (int j = 0; j< simulation.getAmountOfDices();j++){
                int result = random.nextInt(simulation.getAmountOfWalls());
                sum = sum + result;
            }

            i++;
        }
        return sum;
    }
}
