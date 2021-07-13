# DiceThrowSimulation

APLIKACJA RESTOWA DO SYMULOWANIA RZUTÓW KOSTKĄ

RESTOWY ENDPOINT POD ADRESEM http://localhost:8080/api/simulation, przyjmuje dane metodą POST do zasymulowania rzutów
tj. ilość rzutów, ilosć kostek, ilość ścianek w kostkach

Program oblicza i sumuje wyniki rzutów i zapisuje w bazie danych wyniki (baza danych H2)

Metodą GET pod adresem http://localhost:8080/api/simulation można uzyskac listę wszystkich historycznych symulacji

Dodatkowo można wyfiltrować poszczególne symulacje dodając parametr odnośnie ilości ścian pod adresem http://localhost:8080/api/simulation/amountOfWalls/{liczba_scian}

DANE TESTOWE: 

{
"amountOfThrows": 1,
"amountOfDices": 2,
"amountOfWalls": 6 
}

DANE WYJŚCIOWE (suma powinna zwrócić wynik między 2 - 12):

{
    "id": 1,
    "amountOfThrows": 1,
    "amountOfDices": 2,
    "amountOfWalls": 6,
    "sumOfResults": 7
}

Program automatycznie nadaje klucz poszczególnym symulacjom w formie ID

