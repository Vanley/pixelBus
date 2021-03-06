CREATE TABLE passengers(id INT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), tickOfArrival INT, willWaitTo INT, destination VARCHAR(32), onStation INT)
CREATE TABLE stations(id INT PRIMARY KEY, name VARCHAR(32), nextPassengersIn INT, nextPassengersAmount INT, stationSize INT, totalPassengersIn INT, totalPassengersLeft INT)
CREATE TABLE game_data(id INT PRIMARY KEY, cityLevel VARCHAR(16), tick INT, gameSpeed VARCHAR(16))
CREATE TABLE vehicles(id INT PRIMARY KEY, name VARCHAR(32), type VARCHAR(16))