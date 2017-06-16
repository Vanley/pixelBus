CREATE TABLE passengers(id INT PRIMARY KEY, tickOfArrival INT, willWaitTo INT, destination VARCHAR(32), waitingOnStation INT)
CREATE TABLE stations(id INT PRIMARY KEY, name VARCHAR(32), nextPassengersIn INT, nextPassengersAmount INT, stationSize INT)
CREATE TABLE game_data(id INT PRIMARY KEY, cityLevel VARCHAR(512), tick INT, gameSpeed VARCHAR(16))