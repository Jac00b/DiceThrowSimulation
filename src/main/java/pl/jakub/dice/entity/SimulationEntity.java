package pl.jakub.dice.entity;

import lombok.*;

import javax.persistence.*;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int amountOfThrows;
    private int amountOfDices;
    private int amountOfWalls;
    private int sumOfResults;
}
