package pl.jakub.dice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@Setter
@AllArgsConstructor
public class CustomException {

    //CUSTOM JSON ERROR CLASS

    HttpStatus status;
    int error;
    String message;


}
