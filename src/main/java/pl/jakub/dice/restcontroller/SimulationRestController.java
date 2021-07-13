package pl.jakub.dice.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.jakub.dice.exception.CustomException;
import pl.jakub.dice.model.Simulation;
import pl.jakub.dice.service.SimulationService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/simulation")
public class SimulationRestController {

   //DEPENDENCY INJECTIONS
    private SimulationService simulationService;

    public SimulationRestController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    //GETTING ALL THE HISTORICAL SIMULATIONS
    @GetMapping("")
    public List<Simulation> findAll() {
        return simulationService.findAll();
    }

    //GETTING ALL SIMULATIONS WITH GIVEN NUMBER OF WALLS
    @GetMapping("/amountOfWalls/{amountOfWalls}")
    public List<Simulation> findByAmountOfWalls(@PathVariable int amountOfWalls) {
        return simulationService.findByAmountOfWalls(amountOfWalls);
    }

    //GETING DATA FROM JSON AND SAVING IT
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Simulation simulation) {


        if (simulation.getId() != null)
            return ResponseEntity.badRequest().body(new CustomException(HttpStatus.BAD_REQUEST, 400
                    , "Symulacja nie może mieć nadanego ID ręcznie, id nadawany jest automatycznie"));


        if (simulation.getAmountOfWalls() == 0 || simulation.getAmountOfWalls() < 0)
            return ResponseEntity.badRequest().body(new CustomException(HttpStatus.BAD_REQUEST, 400
                    , "Liczba ścian nie może być równa 0 lub mniej"));

        if (simulation.getAmountOfThrows() < 0)
            return ResponseEntity.badRequest().body(new CustomException(HttpStatus.BAD_REQUEST, 400
                    , "Liczba rzutów nie może być mniejsza od 0"));

        if (simulation.getAmountOfDices() < 0)
            return ResponseEntity.badRequest().body(new CustomException(HttpStatus.BAD_REQUEST, 400
                    , "Liczba kostek nie może być mniejsza niż 0"));


        //GETTING SUM AND ADDING IT TO THE OBJECT
        int sum = sumResult(simulation);
        simulation.setSumOfResults(sum);


        Simulation savedSimulation = simulationService.save(simulation);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(savedSimulation.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedSimulation);

    }


    //SUMING METHOD
    private int sumResult(Simulation simulation) {
        int sum = 0;
        int i = 1;
        if (simulation.getAmountOfWalls() == 1) {
            return simulation.getAmountOfDices() * simulation.getAmountOfThrows();
        } else if (simulation.getAmountOfWalls() == 0) {
            return 0;
        }
        while (i <= simulation.getAmountOfThrows()) {
            for (int j = 1; j <= simulation.getAmountOfDices(); j++) {
                int result = (int) (Math.random() * simulation.getAmountOfWalls()) + 1;
                sum = sum + result;
            }

            i++;
        }
        return sum;
    }
}
