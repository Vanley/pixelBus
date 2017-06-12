CREATE TABLE passengers(id INT PRIMARY KEY, tickOfArrival INT, willWaitTo INT, destination VARCHAR(32))
CREATE TABLE stations(id INT PRIMARY KEY, name VARCHAR(32), nextPassengersIn INT, nextPassengersAmount INT, stationSize INT)
CREATE TABLE game(id INT PRIMARY KEY, cityLevel VARCHAR(512), tick INT, gameSpeed INT)