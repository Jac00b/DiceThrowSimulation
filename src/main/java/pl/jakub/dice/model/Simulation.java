package pl.jakub.dice.model;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Simulation {

    //POJO CLASS

    private Long id;
    private int amountOfThrows;
    private int amountOfDices;
    private int amountOfWalls;
    private int sumOfResults;

}
