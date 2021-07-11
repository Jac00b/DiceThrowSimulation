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

@RestController
@RequestMapping("api/simulation")
public class SimulationRestController {
//service injection
    private SimulationService simulationService;

    public SimulationRestController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    //listing all historical throws
    @GetMapping("")
    public List<Simulation> findAll() {
            return simulationService.findAll();
    }

    //LISTING THROWS BY NUMBER OF WALLS
    @GetMapping("/amountOfWalls/{amountOfWalls}")
    public List<Simulation> findByAmountOfWalls(@PathVariable int amountOfWalls) {
        return simulationService.findByAmountOfWalls(amountOfWalls);
    }

//getting data from JSON and saving it to database
    @PostMapping
    public ResponseEntity<Simulation> save(@RequestBody Simulation simulation) {
        if (simulation.getId() != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Zapisywana symulacja nie może miec ustawionego id");

        if (simulation.getAmountOfWalls()==0 || simulation.getAmountOfWalls()<0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Liczba ścian w kostce nie może wynosić 0 lub być ujemna");

        if (simulation.getAmountOfThrows()<0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Liczba rzutów nie może być ujemna");

        if (simulation.getAmountOfDices()<0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Liczba kostek nie może być ujemna");


        //GETING SUM OF THE RESULTS AND ADDING IT TO SIMULATION OBJECT
        int sum = sumResult(simulation);
        simulation.setSumOfResults(sum);

        //AFTER ADDING SUM OF THE RESULT SAVING IT TO DB
        Simulation savedSimulation = simulationService.save(simulation);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(savedSimulation.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedSimulation);
    }


    //METHOD TO SUM THE RESULTS
    private int sumResult(Simulation simulation) {
        int sum = 0;
        int i = 1;
        if (simulation.getAmountOfWalls()==1){
            return simulation.getAmountOfDices() * simulation.getAmountOfThrows();
        } else if (simulation.getAmountOfWalls()==0){
            return 0;
        }
        while (i <= simulation.getAmountOfThrows()){
            for (int j = 1; j<= simulation.getAmountOfDices();j++){
                int result = (int) (Math.random() * simulation.getAmountOfWalls()) +1;
                sum = sum + result;
            }

            i++;
        }
        return sum;
    }
}
