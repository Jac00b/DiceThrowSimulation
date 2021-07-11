package pl.jakub.dice.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "throws")
public class SimulationEntity {

    //klasa encji sumulacji rzutów
    
    @Id
    private long id;
    private int amountOfThrows;
    private int amountOfDices;
    private int amountOfWalls;
    private int sumOfResults;
}